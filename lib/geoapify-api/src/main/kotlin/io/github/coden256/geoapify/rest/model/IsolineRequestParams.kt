package io.github.coden256.geoapify.rest.model

import io.github.coden256.utils.QueryParamEnumType


data class IsolineRequestParams(
    val lat: Double,
    val lon: Double,
    val type: Type,
    val mode: Mode,
    val range: Long,
    val apiKey: String,
)

enum class Mode(override val param: String): QueryParamEnumType {
    WALK("walk"),
    BICYCLE("bicycle"),
    DRIVE("drive"),
    APPROXIMATED_TRANSIT("approximated_transit")
}

enum class Type(override val param: String): QueryParamEnumType {
    DISTANCE("distance"),
    TIME("time"),
}