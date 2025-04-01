package io.github.coden256.nominatim.model;

import com.fasterxml.jackson.annotation.JsonProperty

typealias NominatimSearchResponse = List<NominatimSearchEntry>
data class NominatimSearchEntry(
    @JsonProperty("place_id")
    val placeId: Long?,
    val licence: String?,
    @JsonProperty("osm_type")
    val osmType: String?,
    @JsonProperty("osm_id")
    val osmId: Long?,
    val lat: String?,
    val lon: String?,
    val category: String?,
    val type: String?,
    @JsonProperty("place_rank")
    val placeRank: Long?,
    val importance: Double?,
    val addresstype: String?,
    val name: String?,
    @JsonProperty("display_name")
    val displayName: String?,
    val address: Address?,
    val boundingbox: List<String>?,
)

data class Address(
    val road: String?,
    val suburb: String?,
    @JsonProperty("city_district")
    val cityDistrict: String?,
    val city: String?,
    val state: String?,
    @JsonProperty("ISO3166-2-lvl4")
    val iso31662Lvl4: String?,
    val postcode: String?,
    val country: String?,
    @JsonProperty("country_code")
    val countryCode: String?,
)
