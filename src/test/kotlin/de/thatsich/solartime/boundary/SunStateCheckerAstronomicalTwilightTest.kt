package de.thatsich.solartime.boundary

import de.thatsich.solartime.boundary.SolarTime.isAstronomicalTwilight
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SunStateCheckerAstronomicalTwilightTest {
    @Test
    fun testIsAstronomicalTwilight()
        {
                val day = ZonedDateTime.of(2019, 1, 24, 6, 30, 0, 0, ZoneId.of("Europe/Berlin"))
            val latitude = 51.449680
            val longitude = 6.973370

            val actual = isAstronomicalTwilight(day, latitude, longitude)

            Assertions.assertThat(actual)
                .isTrue()
        }
}