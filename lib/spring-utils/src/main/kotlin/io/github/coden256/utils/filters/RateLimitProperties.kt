package io.github.coden256.utils.filters

import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

data class RateLimitProperties(
    val name: String = "request-limit",
    val requests: Int = 1,
    val every: java.time.Duration = 10.seconds.toJavaDuration(),
    val timeout: java.time.Duration = 1.minutes.toJavaDuration(),
    // if the (number of submitted requests in a queue * refreshPeriod)/requests is greater than timeout an exception is thrown prematurely
) {

    fun maxAllowedInstantRequests(): Double {
        return asRateLimiterConfig().maxAllowedInstantRequests()
    }

    fun asRateLimiterConfig(): RateLimiterConfig {
        return RateLimiterConfig.custom()
            .limitForPeriod(requests)
            .limitRefreshPeriod(every)
            .timeoutDuration(timeout)
            .build()
    }

    fun asRateLimiter(): RateLimiter {
        return RateLimiter.of(name, asRateLimiterConfig())
    }

    companion object {
        fun RateLimiterConfig.maxAllowedInstantRequests(): Double {
            return (timeoutDuration.toNanos() * limitForPeriod).toDouble() / limitRefreshPeriod.toNanos()
        }
    }
}