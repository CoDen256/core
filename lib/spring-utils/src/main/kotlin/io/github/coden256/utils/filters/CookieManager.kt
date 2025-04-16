package io.github.coden256.utils.filters

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.http.ResponseCookie
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import reactor.core.publisher.Mono

class CookieManager : Logging {

    private val saved: MutableMap<String, ResponseCookie> = HashMap()

    fun readCookies() = ExchangeFilterFunction { request: ClientRequest, next: ExchangeFunction ->
        Mono.just(request)
            .map { it.withCookies(saved) }
            .doOnNext { logger.info("[CookieReader] Reading cookies before ${it.url()}: ${it.cookies()}") }
            .flatMap { next.exchange(it) } // invoke request
    }

    fun saveCookies() = ExchangeFilterFunction { request: ClientRequest, next: ExchangeFunction ->
        Mono.just(request)
            .flatMap { next.exchange(it) } // invoke request
            .doOnNext { logger.info("[CookieWriter] Writing cookies after ${it.request().uri}: ${it.cookies()}") }
            .doOnNext { saveCookies(it) }
    }

    private fun ClientRequest.withCookies(cookies: Map<String, ResponseCookie>) =
        ClientRequest.from(this)
            .cookies { c -> cookies.forEach { (key, cookie) -> c.add(key, cookie.value) } }
            .build()

    private fun saveCookies(response: ClientResponse) {
        response.cookies().forEach { cookie ->
            cookie.value.forEach {
                saved[cookie.key] = it
            }
        }
    }
}