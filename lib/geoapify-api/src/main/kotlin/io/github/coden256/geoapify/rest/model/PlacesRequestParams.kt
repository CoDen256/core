package io.github.coden256.geoapify.rest.model

import io.github.coden256.utils.QueryParamEnumType


data class PlacesRequestParams(
    val categories: List<Category>,
    val filter: Filter,
    val bias: Bias,
    val limit: Long,
    val apiKey: String,
)

data class Filter(
    val geometryId: String,
){
    override fun toString(): String {
        return "geometry:$geometryId"
    }
}

data class Bias(
    val lon: Double,
    val lat: Double
){
    override fun toString(): String {
        return "proximity:$lon,$lat"
    }
}


enum class Category(override val param: String): QueryParamEnumType {
   COMMERCIAL_SUPERMARKET("commercial.supermarket"),
   COMMERCIAL_MARKETPLACE("commercial.marketplace"),
   COMMERCIAL_SHOPPING_MALL("commercial.shopping_mall"),
    PUBLIC_TRANSPORT("public_transport")
}