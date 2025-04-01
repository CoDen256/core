package io.github.coden256.nominatim

import reactor.core.publisher.Mono

interface Nominatim {
    fun getStreetDetails(request: StreetDetailsRequest): Mono<StreetDetailsResponse>
}

data class StreetDetailsRequest(val street: String, val city: String)
data class StreetDetailsResponse(val type: String)