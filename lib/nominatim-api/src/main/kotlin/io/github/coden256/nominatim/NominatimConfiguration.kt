package io.github.coden256.nominatim

import io.github.coden256.utils.filters.RateLimiterFilter
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@EnableConfigurationProperties(NominatimAPIProperties::class)
class NominatimConfiguration {

    @Bean
    fun nominatimWebClient(properties: NominatimAPIProperties, nominatimThrottle: ExchangeFilterFunction): WebClient {
        return WebClient
            .builder()
            .baseUrl(properties.host)
            .defaultHeaders { headers ->
                properties.headers.forEach { headers.add(it.key, it.value) }
            }
            .filter(nominatimThrottle)
            .build()
    }

    @Bean
    fun nominatimApi(nominatimWebClient: WebClient): Nominatim {
        return NominatimRestAPI(nominatimWebClient)
    }

    @Bean
    fun nominatimThrottle(properties: NominatimAPIProperties): ExchangeFilterFunction {
        return RateLimiterFilter(properties.limit.asRateLimiter())
    }
}