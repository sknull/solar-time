package de.visualdigits.solartime.boundary

import de.visualdigits.solartime.SolarTime.calculateNauticalDawn
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SolarTimeNauticalDawnTest {

    @Test
    fun testIsPresent() {
        val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = calculateNauticalDawn(day, latitude, longitude)

        org.junit.jupiter.api.Assertions.assertNotNull(actual)
    }

    @Test
    fun testIsExact() {
        val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = calculateNauticalDawn(day, latitude, longitude)

        assertEquals(ZonedDateTime.of(2019, 6, 24, 3, 15, 15, 0, ZoneId.of("Europe/Berlin")), actual)
    }
}
