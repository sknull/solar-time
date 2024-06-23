package de.visualdigits.solartime.boundary

import de.visualdigits.solartime.boundary.SolarTime.isDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SunStateCheckerIsDayTest {

    @Test
    @DisplayName("At 2019-06-24T12:00:00+02:00[Europe/Berlin] is Day in Europe")
    fun happyPath_dayIsAfterSunriseAndBeforeSunset() {
        val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isDay(day, latitude, longitude)

        assertTrue(actual)
    }

    @Test
    @DisplayName("At 2019-06-24T01:00:00+02:00[Europe/Berlin] is before Day in Europe")
    fun beforeDay() {
        val day = ZonedDateTime.of(2019, 6, 24, 1, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isDay(day, latitude, longitude)

        assertFalse(actual)
    }

    @Test
    @DisplayName("At 2019-06-24T23:00:00+02:00[Europe/Berlin] is after Day in Europe")
    fun afterDay() {
        val day = ZonedDateTime.of(2019, 6, 24, 23, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isDay(day, latitude, longitude)

        assertFalse(actual)
    }

    @DisplayName("North Pole")
    @ParameterizedTest(name = "Month {0} should be {1}")
    @CsvSource(
        "1, false",
        "2, true",
        "3, true",
        "4, true",
        "5, true",
        "6, true",
        "7, false",
        "8, true",
        "9, true",
        "10, true",
        "11, false",
        "12, false"
    )
    fun checkNorthPole(month: Int, expected: Boolean) {
        val day = ZonedDateTime.of(2019, month, 24, 12, 0, 0, 0, ZoneId.of("Europe/Istanbul"))
        val latitude = 69.660716
        val longitude = 18.925278

        val actual = isDay(day, latitude, longitude)

        assertEquals(expected, actual)
    }


    @DisplayName("South Pole")
    @ParameterizedTest(name = "Month {0} should be {1}")
    @CsvSource(
        "1, true",
        "2, true",
        "3, false",
        "4, false",
        "5, false",
        "6, false",
        "7, false",
        "8, false",
        "9, true",
        "10, true",
        "11, true",
        "12, true"
    )
    fun checkSouthPole(month: Int, expected: Boolean) {
        val day = ZonedDateTime.of(2019, month, 24, 12, 0, 0, 0, ZoneId.of("Europe/Istanbul"))
        val latitude = -90
        val longitude = 0

        val actual = isDay(day, latitude.toDouble(), longitude.toDouble())

        assertEquals(expected, actual)
    }
}
