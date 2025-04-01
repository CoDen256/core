package io.github.coden256.utils

import org.springframework.util.MultiValueMap
import org.springframework.util.LinkedMultiValueMap

fun parseQueryParamsMulti(request: Any): MultiValueMap<String, String> {
    val mapValues = parseQueryParams(request).mapValues { listOf(it.value) }
    return LinkedMultiValueMap(mapValues)
}
