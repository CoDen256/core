package io.github.coden256.utils

import com.github.ajalt.mordant.rendering.TextColors
import org.junit.jupiter.api.Test

class StringsKtTest {

    @Test
    fun bg() {
        println(TextColors.brightRed("Hello world!"))
        println(TextColors.brightGreen("Hello world!"))
        println(TextColors.brightGreen.bg("Hello world!"))
        println(TextColors.brightRed.bg("Hello world!"))
    }
}