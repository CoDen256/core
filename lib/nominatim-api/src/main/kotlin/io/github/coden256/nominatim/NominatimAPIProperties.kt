package io.github.coden256.nominatim

import io.github.coden256.utils.filters.RateLimitProperties
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("api.nominatim")
data class NominatimAPIProperties(
    val host: String,
    val headers: Map<String, String>,
    val limit: RateLimitProperties
)


