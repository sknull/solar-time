package de.thatsich.solartime.boundary

import de.thatsich.solartime.boundary.SolarTime.calculateCivilDusk
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SolarTimeCivilDuskTest {
    @Test
    fun testCivilDuskIsPresent() {
        val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = calculateCivilDusk(day, latitude, longitude)

        assertNotNull(actual)
    }
}
