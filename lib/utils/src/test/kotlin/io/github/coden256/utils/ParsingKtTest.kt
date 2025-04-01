package io.github.coden256.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ParsingKtTest {

    @Test
    fun parsePriceTest() {
        assertEquals(540.0, parsePrice("540 € (zzgl. Heizkosten)"))
        assertEquals(940.0, parsePrice("ca. 940 €"))
        assertEquals(416.0, parsePrice("416 €"))
        assertEquals(9.22, parsePrice("9,22 €/m²"))
        assertEquals(65.0, parsePrice("65 €"))
        assertEquals(133.50, parsePrice("133,50 €"))
        assertEquals(1248.0, parsePrice("1248,00 EUR"))
        assertEquals(614.50, parsePrice("614,50 €"))
        assertEquals(0.2908, parsePrice("Arbeitspreis: <b>29,08 Ct./kWh</b>"))
        assertEquals(15.77, parsePrice("Grundpreis: <b>15,77 €/Monat</b>"))
    }

}