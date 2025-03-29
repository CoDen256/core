package io.github.coden256.utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ResultsTest {
    @Test
    fun success() {
        assertTrue("".success().isSuccess)
        assertFalse("".success().isFailure)
        assertTrue("".notNullOrFailure().isSuccess)
        assertFalse(null.notNullOrFailure().isSuccess)
    }
}