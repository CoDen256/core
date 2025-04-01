package io.github.coden256.utils

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import roundTo

class NumbersTest {

    @Test
    fun roundTo() {
        assertEquals(540.27, 540.2666.roundTo(2))
        assertEquals(0.291, 0.2908.roundTo(3))
        assertEquals(0.2908, 0.2908.roundTo(4))
    }
}