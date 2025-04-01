package io.github.coden256.geoapify

import io.github.coden256.geoapify.rest.GeoapifyRestAPI
import io.github.coden256.utils.filters.RateLimiterFilter
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@EnableConfigurationProperties(GeoapifyAPIProperties::class)
class GeoapifyConfiguration {

    @Bean
    fun geoWebClient(properties: GeoapifyAPIProperties, geoThrottle: ExchangeFilterFunction): WebClient {
        return WebClient
            .builder()
            .baseUrl(properties.host)
            .filter(geoThrottle)
            .build()
    }

    @Bean
    fun geoApi(geoWebClient: WebClient, properties: GeoapifyAPIProperties): Geoapify {
        return GeoapifyRestAPI(geoWebClient, properties.apiToken)
    }

    @Bean
    fun geoThrottle(properties: GeoapifyAPIProperties): ExchangeFilterFunction {
        return RateLimiterFilter(properties.limit.asRateLimiter())
    }
}