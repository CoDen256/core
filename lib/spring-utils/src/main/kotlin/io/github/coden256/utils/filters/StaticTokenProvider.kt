package io.github.coden256.utils.filters

import reactor.core.publisher.Mono

class StaticTokenProvider(private val token: String): TokenProvider {
    override fun getToken(): Mono<String> {
        return Mono.just(token)
    }
    companion object{
        fun justToken(token: String): StaticTokenProvider {
            return StaticTokenProvider(token)
        }
    }
}