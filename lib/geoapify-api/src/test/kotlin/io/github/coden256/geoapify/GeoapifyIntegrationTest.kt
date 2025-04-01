package io.github.coden256.geoapify

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.time.Duration.Companion.minutes

@SpringBootTest(classes = [GeoapifyConfiguration::class])
@Disabled
class GeoapifyIntegrationTest {

    @Autowired
    private lateinit var geoapify: Geoapify


    @Test
    fun getStreetDetails() {
        val details = geoapify.getReachability(ReachabilityRequest(51.3633503444741, 12.362456547050638, 5.minutes))

        assertTrue(details.block()!!.places.size > 5)
    }
}