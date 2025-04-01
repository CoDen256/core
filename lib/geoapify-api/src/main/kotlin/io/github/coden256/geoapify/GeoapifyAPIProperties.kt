package io.github.coden256.geoapify

import io.github.coden256.utils.filters.RateLimitProperties
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("api.geoapify")
data class GeoapifyAPIProperties(
    val host: String,
    val apiToken: String,
    val limit: RateLimitProperties
)
