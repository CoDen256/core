package io.github.coden256.geoapify.rest.model

data class IsolineResponse(
    val features: List<IsolineFeature>,
    val type: String?,
    val properties: IsolineProperties?,
)

data class IsolineFeature(
    val properties: FeatureProperties?,
    val geometry: IsolineGeometry?,
    val type: String?,
)

data class FeatureProperties(
    val lat: Double?,
    val lon: Double?,
    val mode: String?,
    val type: String?,
    val range: Long?,
    val id: String?,
)

data class IsolineGeometry(
    val coordinates: List<List<List<List<Double>>>>,
    val type: String?,
)

data class IsolineProperties(
    val id: String?,
)
