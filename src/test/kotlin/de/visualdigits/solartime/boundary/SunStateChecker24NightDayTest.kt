package de.visualdigits.solartime.boundary

import de.visualdigits.solartime.boundary.SolarTime.is24HourNightTime
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SunStateChecker24NightDayTest {
    @Test
    fun testIsAbsent()
        {
                val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
            val latitude = 51.449680
            val longitude = 6.973370

            val actual = is24HourNightTime(day, latitude, longitude)

            Assertions.assertThat(actual)
                .isFalse()
        }

    @Test
    fun testIsPresent()
        {
                val day = ZonedDateTime.of(2019, 12, 24, 12, 0, 0, 0, ZoneId.of("Europe/Istanbul"))
            val latitude = 69.660716
            val longitude = 18.925278

            val actual = is24HourNightTime(day, latitude, longitude)

            Assertions.assertThat(actual)
                .isTrue()
        }
}
