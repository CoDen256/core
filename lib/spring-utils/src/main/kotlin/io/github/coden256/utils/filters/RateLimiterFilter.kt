package io.github.coden256.utils.filters

import io.github.coden256.utils.filters.RateLimitProperties.Companion.maxAllowedInstantRequests
import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.reactor.ratelimiter.operator.RateLimiterOperator
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import reactor.core.publisher.Mono

class RateLimiterFilter(private val rateLimiter: RateLimiter) : ExchangeFilterFunction, Logging {
//    override val name: String TODO proper logging delegation
//        get() = rateLimiter.name + "-" + super.name

    @EventListener(ApplicationReadyEvent::class)
    fun start(){
        val cfg = rateLimiter.rateLimiterConfig
        logger.info { "Limiting request rate at {${cfg.limitForPeriod} requests every ${cfg.limitRefreshPeriod.seconds}s. " +
                "Max: ${cfg.timeoutDuration.seconds}s or ${cfg.maxAllowedInstantRequests()} requests/instant }" }
    }
    override fun filter(request: ClientRequest, next: ExchangeFunction): Mono<ClientResponse> {
        return Mono
            .just(request)
            .transformDeferred(RateLimiterOperator.of(rateLimiter))
            .flatMap {
                next
                    .exchange(it)
                    .doOnSubscribe {
                        logger.info { "Executing request ${request.method()} ${request.url()}" }
                        logger.debug { "Request body: ${request.body()}" }
                    }
            }
    }
}