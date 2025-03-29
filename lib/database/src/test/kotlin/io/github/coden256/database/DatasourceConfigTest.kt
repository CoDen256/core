package io.github.coden256.database

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class DatasourceConfigTest {

    @Test
    fun getUrl() {
        val (url, user, password, driverClassName) = DatasourceConfig(
            url = "jdbc:h2:mem:test",
            user = "user",
            password = "pass"
        )
        assertEquals("jdbc:h2:mem:test", url)
        assertEquals("user", user)
        assertEquals("pass", password)
        assertEquals(null, driverClassName)
    }
}