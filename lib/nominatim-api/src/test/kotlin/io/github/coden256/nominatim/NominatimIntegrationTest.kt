package io.github.coden256.nominatim

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [NominatimConfiguration::class])
@Disabled
class NominatimIntegrationTest {

    @Autowired
    private lateinit var nominatim: Nominatim


    @Test
    fun getStreetDetails() {
        val details = nominatim.getStreetDetails(StreetDetailsRequest("Eisenbahn Str", "Leipzig"))

        assertEquals("retail", details.block()!!.type)
    }
}