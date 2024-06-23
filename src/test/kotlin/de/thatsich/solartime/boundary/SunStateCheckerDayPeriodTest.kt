package de.thatsich.solartime.boundary

import de.thatsich.solartime.boundary.SolarTime.getDayPeriod
import de.thatsich.solartime.entity.DayPeriod
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SunStateCheckerDayPeriodTest {
    @Test
    fun testIsDay()
        {
                val day = ZonedDateTime.of(2019, 1, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
            val latitude = 51.449680
            val longitude = 6.973370

            val actual = getDayPeriod(day, latitude, longitude)

            Assertions.assertThat(actual)
                .isEqualTo(DayPeriod.DAY)
        }

    @Test
    fun testIsCivilTwilight()
        {
                val day = ZonedDateTime.of(2019, 1, 24, 8, 0, 0, 0, ZoneId.of("Europe/Berlin"))
            val latitude = 51.449680
            val longitude = 6.973370

            val actual = getDayPeriod(day, latitude, longitude)

            Assertions.assertThat(actual)
                .isEqualTo(DayPeriod.CIVIL_TWILIGHT)
        }

    @Test
    fun testIsNauticalTwilight()
        {
                val day = ZonedDateTime.of(2019, 1, 24, 7, 30, 0, 0, ZoneId.of("Europe/Berlin"))
            val latitude = 51.449680
            val longitude = 6.973370

            val actual = getDayPeriod(day, latitude, longitude)

            Assertions.assertThat(actual)
                .isEqualTo(DayPeriod.NAUTICAL_TWILIGHT)
        }

    @Test
    fun testIsAstronomicalTwilight()
        {
                val day = ZonedDateTime.of(2019, 1, 24, 7, 0, 0, 0, ZoneId.of("Europe/Berlin"))
            val latitude = 51.449680
            val longitude = 6.973370

            val actual = getDayPeriod(day, latitude, longitude)

            Assertions.assertThat(actual)
                .isEqualTo(DayPeriod.ASTRONOMICAL_TWILIGHT)
        }

    @Test
    fun testIsNight()
        {
                val day = ZonedDateTime.of(2019, 1, 24, 6, 0, 0, 0, ZoneId.of("Europe/Berlin"))
            val latitude = 51.449680
            val longitude = 6.973370

            val actual = getDayPeriod(day, latitude, longitude)

            Assertions.assertThat(actual)
                .isEqualTo(DayPeriod.NIGHT)
        }
}