package io.github.coden256.utils.filters

import org.junit.jupiter.api.Test
import reactor.kotlin.test.test

class StaticTokenProviderTest {

    @Test
    fun getToken() {
        StaticTokenProvider("abc")
            .getToken()
            .test()
            .expectNext("abc")
            .verifyComplete()
    }
}