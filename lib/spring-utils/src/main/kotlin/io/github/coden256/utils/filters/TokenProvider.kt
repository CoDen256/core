package io.github.coden256.utils.filters

import reactor.core.publisher.Mono

interface TokenProvider {
    fun getToken(): Mono<String>
}