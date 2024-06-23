package de.visualdigits.solartime

import de.visualdigits.solartime.SolarTime.getDayPeriod
import de.visualdigits.solartime.model.DayPeriod
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class SolarTimeCheckerDayPeriodTest {

    @Test
    fun testIsDay() {
        val day = ZonedDateTime.of(2019, 1, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = getDayPeriod(day, latitude, longitude)

        assertEquals(DayPeriod.DAY, actual)
    }

    @Test
    fun testIsCivilTwilight() {
        val day = ZonedDateTime.of(2019, 1, 24, 8, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = getDayPeriod(day, latitude, longitude)

        assertEquals(DayPeriod.CIVIL_TWILIGHT, actual)
    }

    @Test
    fun testIsNauticalTwilight() {
        val day = ZonedDateTime.of(2019, 1, 24, 7, 30, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = getDayPeriod(day, latitude, longitude)

        assertEquals(DayPeriod.NAUTICAL_TWILIGHT, actual)
    }

    @Test
    fun testIsAstronomicalTwilight() {
        val day = ZonedDateTime.of(2019, 1, 24, 7, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = getDayPeriod(day, latitude, longitude)

        assertEquals(DayPeriod.ASTRONOMICAL_TWILIGHT, actual)
    }

    @Test
    fun testIsNight() {
        val day = ZonedDateTime.of(2019, 1, 24, 6, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = getDayPeriod(day, latitude, longitude)

        assertEquals(DayPeriod.NIGHT, actual)
    }
}
