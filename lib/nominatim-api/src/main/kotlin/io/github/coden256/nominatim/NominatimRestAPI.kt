package io.github.coden256.nominatim

import StreetDetailsParams
import io.github.coden256.nominatim.model.NominatimSearchResponse
import io.github.coden256.utils.parseQueryParamsMulti
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

class NominatimRestAPI(private val webClient: WebClient) : Nominatim {
    override fun getStreetDetails(request: StreetDetailsRequest): Mono<StreetDetailsResponse> {
        val params = StreetDetailsParams(1, formatQuery(request), "jsonv2", 1)
        return webClient
            .get()
            .uri{uri -> uri
                .path("/search")
                .queryParams(parseQueryParamsMulti(params))
                .build()
            }
            .retrieve()
            .bodyToMono<NominatimSearchResponse>()
            .mapNotNull<StreetDetailsResponse> { parseResponse(it) }
            .switchIfEmpty(Mono.error(IllegalStateException("No details found or details are invalid")))
    }

    private fun parseResponse(response: NominatimSearchResponse): StreetDetailsResponse? {
        return response.getOrNull(0)?.type?.let { StreetDetailsResponse(it) }
    }

    private fun formatQuery(request: StreetDetailsRequest) : String{
        return "${request.street}+${request.city}"
    }

}