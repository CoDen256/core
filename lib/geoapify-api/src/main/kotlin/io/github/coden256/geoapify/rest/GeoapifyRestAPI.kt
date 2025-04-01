package io.github.coden256.geoapify.rest

import io.github.coden256.geoapify.Geoapify
import io.github.coden256.geoapify.ReachabilityRequest
import io.github.coden256.geoapify.ReachabilityResponse
import io.github.coden256.geoapify.ReachablePlace
import io.github.coden256.geoapify.rest.model.*
import io.github.coden256.utils.parseQueryParamsMulti
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

class GeoapifyRestAPI(private val webClient: WebClient, private val apiKey: String) : Geoapify {
    override fun getReachability(request: ReachabilityRequest): Mono<ReachabilityResponse> {
        val isolineParams = isolineRequestParams(request)
        return getIsoline(isolineParams)
            .mapNotNull<String> { getGeometryId(it) }
            .switchIfEmpty(Mono.error(IllegalStateException("Cannot find isoline id: $isolineParams")))
            .flatMap { getPlaces(placesRequestParams(request, it)) }
            .map { it.features }
            .map { parsePlaces(it) }
    }

    private fun isolineRequestParams(request: ReachabilityRequest): IsolineRequestParams {
        return IsolineRequestParams(request.lat, request.lon, Type.TIME, Mode.WALK, request.time.inWholeSeconds, apiKey)
    }

    private fun placesRequestParams(request: ReachabilityRequest, geoId: String): PlacesRequestParams {
        return PlacesRequestParams(
            categories = Category.entries,
            limit = 20,
            filter = Filter(geoId),
            bias = Bias(request.lon, request.lat),
            apiKey = apiKey,
        )
    }

    private fun parsePlaces(places: List<PlacesFeature>) =
        ReachabilityResponse(places.map { parsePlace(it) })

    private fun parsePlace(place: PlacesFeature): ReachablePlace {
        return ReachablePlace(
            place.properties.categories.filter { filterCategories(it) }.joinToString(","),
            place.properties.name,
            place.properties.distance,
            place.properties.lat,
            place.properties.lon
        )
    }

    private fun filterCategories(it: String): Boolean {
        return it.contains("public_transport\\.|commercial\\.".toRegex())
    }


    private fun getGeometryId(response: IsolineResponse): String? {
        return response.properties?.id
    }

    private fun getPlaces(params: PlacesRequestParams): Mono<PlacesResponse> {
        return webClient
            .get()
            .uri { uri ->
                uri
                    .path("/v2/places")
                    .queryParams(parseQueryParamsMulti(params))
                    .build()
            }
            .retrieve()
            .bodyToMono<PlacesResponse>()
    }

    private fun getIsoline(params: IsolineRequestParams): Mono<IsolineResponse> {
        return webClient
            .get()
            .uri { uri ->
                uri
                    .path("/v1/isoline")
                    .queryParams(parseQueryParamsMulti(params))
                    .build()
            }
            .retrieve()
            .bodyToMono<IsolineResponse>()
    }

}