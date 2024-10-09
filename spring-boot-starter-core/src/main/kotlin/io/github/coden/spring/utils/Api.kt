package io.github.coden.spring.utils

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import parseQueryParams

fun parseQueryParamsMulti(request: Any): MultiValueMap<String, String> {
    val mapValues = parseQueryParams(request).mapValues { listOf(it.value) }
    return LinkedMultiValueMap(mapValues)
}