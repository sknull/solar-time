package de.thatsich.solartime.boundary

import de.thatsich.solartime.boundary.SolarTime.calculateCivilDawn
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SolarTimeCivilDawnTest {
    @Test
    fun testCivilDawnIsPresent() {
        val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = calculateCivilDawn(day, latitude, longitude)

        assertNotNull(actual)
    }
}
