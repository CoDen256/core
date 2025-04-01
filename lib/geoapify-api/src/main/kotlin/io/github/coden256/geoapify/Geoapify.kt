package io.github.coden256.geoapify

import reactor.core.publisher.Mono
import kotlin.time.Duration

interface Geoapify {
    fun getReachability(request: ReachabilityRequest): Mono<ReachabilityResponse>
}

data class ReachabilityRequest(val lat: Double, val lon: Double, val time: Duration)
data class ReachabilityResponse(val places: List<ReachablePlace>)
data class ReachablePlace(val type: String, val name: String, val distance: Long, val lat: Double, val lon: Double) {}