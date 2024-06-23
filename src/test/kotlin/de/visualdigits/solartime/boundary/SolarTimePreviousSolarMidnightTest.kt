package de.visualdigits.solartime.boundary

import de.visualdigits.solartime.SolarTime.calculatePreviousSolarMidnight
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SolarTimePreviousSolarMidnightTest {
    @Test
    fun solarMidnightIsNotPresent() {
        val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = calculatePreviousSolarMidnight(day, latitude, longitude)

        assertNull(actual)
    }

    @Test
    fun solarMidnightIsPresent() {
        val day = ZonedDateTime.of(2019, 1, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = calculatePreviousSolarMidnight(day, latitude, longitude)

        assertNotNull(actual)
    }
}
