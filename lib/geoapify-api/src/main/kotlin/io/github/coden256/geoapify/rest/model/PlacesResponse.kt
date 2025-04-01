package io.github.coden256.geoapify.rest.model

import com.fasterxml.jackson.annotation.JsonProperty


data class PlacesResponse(
    val type: String,
    val features: List<PlacesFeature>,
)

data class PlacesFeature(
    val type: String?,
    val properties: PlacesProperties,
    val geometry: PlacesGeometry?,
)

data class PlacesProperties(
    val name: String,
    val ref: Long?,
    val country: String?,
    @JsonProperty("country_code")
    val countryCode: String?,
    val state: String?,
    val city: String?,
    val postcode: String?,
    val district: String?,
    val suburb: String?,
    val street: String?,
    val housenumber: String?,
    val lon: Double,
    val lat: Double,
    @JsonProperty("state_code")
    val stateCode: String?,
    val formatted: String?,
    @JsonProperty("address_line1")
    val addressLine1: String?,
    @JsonProperty("address_line2")
    val addressLine2: String?,
    val categories: List<String>,
    val details: List<String>?,
    val datasource: Datasource?,
    val website: String?,
    @JsonProperty("opening_hours")
    val openingHours: String?,
    val brand: String?,
    @JsonProperty("brand_details")
    val brandDetails: BrandDetails?,
    val operator: String?,
    val contact: Contact?,
    val facilities: Facilities?,
    val building: Building?,
    val commercial: Commercial?,
    val distance: Long,
    @JsonProperty("place_id")
    val placeId: String?,
    @JsonProperty("payment_options")
    val paymentOptions: PaymentOptions?,
)

data class Datasource(
    val sourcename: String?,
    val attribution: String?,
    val license: String?,
    val url: String?,
    val raw: Raw?,
)

data class Raw(
    val ref: Long?,
    val name: String?,
    val shop: String?,
    val brand: String?,
    @JsonProperty("osm_id")
    val osmId: Long?,
    val website: String?,
    val building: String?,
    val operator: String?,
    @JsonProperty("osm_type")
    val osmType: String?,
    @JsonProperty("addr:city")
    val addrCity: String?,
    val wheelchair: String?,
    @JsonProperty("addr:street")
    val addrStreet: String?,
    @JsonProperty("addr:country")
    val addrCountry: String?,
    @JsonProperty("operator:tax")
    val operatorTax: String?,
    @JsonProperty("addr:postcode")
    val addrPostcode: String?,
    @JsonProperty("contact:email")
    val contactEmail: String?,
    @JsonProperty("contact:phone")
    val contactPhone: String?,
    @JsonProperty("opening_hours")
    val openingHours: String?,
    @JsonProperty("brand:wikidata")
    val brandWikidata: String?,
    @JsonProperty("brand:wikipedia")
    val brandWikipedia: String?,
    @JsonProperty("addr:housenumber")
    val addrHousenumber: Any?,
    val level: Long?,
    val organic: String?,
    val mapillary: Long?,
    @JsonProperty("payment:cash")
    val paymentCash: String?,
    @JsonProperty("air_conditioning")
    val airConditioning: String?,
    @JsonProperty("payment:girocard")
    val paymentGirocard: String?,
    @JsonProperty("payment:debit_cards")
    val paymentDebitCards: String?,
    @JsonProperty("payment:credit_cards")
    val paymentCreditCards: String?,
    @JsonProperty("check_date:opening_hours")
    val checkDateOpeningHours: String?,
    val phone: String?,
    @JsonProperty("roof:shape")
    val roofShape: String?,
    @JsonProperty("self_checkout")
    val selfCheckout: String?,
    @JsonProperty("building:levels")
    val buildingLevels: Long?,
    @JsonProperty("internet_access")
    val internetAccess: String?,
    @JsonProperty("payment:google_pay")
    val paymentGooglePay: String?,
    @JsonProperty("payment:contactless")
    val paymentContactless: String?,
    @JsonProperty("start_date")
    val startDate: String?,
    @JsonProperty("addr:suburb")
    val addrSuburb: String?,
    @JsonProperty("roof:levels")
    val roofLevels: Long?,
    @JsonProperty("addr:housename")
    val addrHousename: String?,
)

data class BrandDetails(
    val wikidata: String?,
    val wikipedia: String?,
)

data class Contact(
    val phone: String?,
    val email: String?,
)

data class Facilities(
    val wheelchair: Boolean?,
    @JsonProperty("wheelchair_details")
    val wheelchairDetails: WheelchairDetails?,
    @JsonProperty("air_conditioning")
    val airConditioning: Boolean?,
    @JsonProperty("internet_access")
    val internetAccess: Boolean?,
)

data class WheelchairDetails(
    val condition: String?,
)

data class Building(
    val type: String?,
    val levels: Long?,
    @JsonProperty("start_date")
    val startDate: String?,
    val roof: Roof?,
)

data class Roof(
    val shape: String?,
    val levels: Long?,
)

data class Commercial(
    val type: String?,
    val organic: Boolean?,
    val level: Long?,
)

data class PaymentOptions(
    val cash: Boolean?,
    @JsonProperty("google_pay")
    val googlePay: Boolean?,
    val contactless: Boolean?,
    @JsonProperty("debit_cards")
    val debitCards: Boolean?,
    @JsonProperty("credit_cards")
    val creditCards: Boolean?,
    val girocard: Boolean?,
)

data class PlacesGeometry(
    val type: String?,
    val coordinates: List<Double>?,
)
