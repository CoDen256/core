import io.github.coden256.utils.QueryParam


data class StreetDetailsParams(
    @QueryParam("addressdetails")
    val addressDetails: Int,
    @QueryParam("q")
    val query: String,
    val format: String,
    val limit: Int)