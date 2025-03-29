package io.github.coden256.telegram.abilities

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class AbilitiesKtTest {

    @Test
    fun allowedUpdates() {
        assertTrue(ALLOWED_UPDATES.contains("message_reaction"))
    }
}