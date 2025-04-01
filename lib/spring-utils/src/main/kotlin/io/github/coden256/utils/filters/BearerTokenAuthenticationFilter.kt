package io.github.coden256.utils.filters

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import reactor.core.publisher.Mono
import java.util.concurrent.atomic.AtomicReference

class BearerTokenAuthenticationFilter(private val tokenProvider: TokenProvider):
    ExchangeFilterFunction, Logging{ // TODO proper Logging

    private val token = AtomicReference<String>()
    private fun requestNewToken(): Mono<String> {
        return tokenProvider
            .getToken()
            .doOnNext {
                token.set(it)
                logger.info { "${tokenProvider.javaClass.simpleName} Got token $it" }
            }
    }

    private fun getToken(): Mono<String> {
        return Mono.justOrEmpty(token.get())
            .switchIfEmpty(requestNewToken())
    }

    override fun filter(request: ClientRequest, next: ExchangeFunction): Mono<ClientResponse> {
        return getToken()
                .flatMap { token -> next.exchange(request.withToken(token)) }
                .flatMap { response ->
                    // If response is 401, retry with a new token
                    if (isUnauthorized(response)) {
                        logger.info { "Unauthorized! Requesting new token"  }
                        requestNewToken()
                            .flatMap { token -> next.exchange(request.withToken(token)) }
                    } else {
                        Mono.just(response)
                    }
                }
        }

    private fun isUnauthorized(response: ClientResponse) =
        response.statusCode() === HttpStatus.UNAUTHORIZED

    private fun ClientRequest.withToken(token: String): ClientRequest {
        return ClientRequest.from(this)
                    .headers { it[HttpHeaders.AUTHORIZATION] = "Bearer $token" }
                    .build()
    }
}