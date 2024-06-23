package de.visualdigits.solartime.boundary

import de.visualdigits.solartime.SolarTime.calculateSolarNoon
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SolarTimeSolarNoonTest {
    @Test
    fun solarNoonIsPresent() {
        val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = calculateSolarNoon(day, latitude, longitude)

        assertNotNull(actual)
    }
}
