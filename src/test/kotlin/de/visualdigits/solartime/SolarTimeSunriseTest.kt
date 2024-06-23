package de.visualdigits.solartime

import de.visualdigits.solartime.SolarTime.calculateSunrise
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import java.time.ZonedDateTime

class SolarTimeSunriseTest {

    @DisplayName("sunrise exists")
    @ParameterizedTest(name = "{index}. {0} => day={1}, latitude={2}, longitude={3}")
    @CsvFileSource(numLinesToSkip = 1, resources = ["sunrise-sunet.csv"])
    fun testSunriseExists(
        @Suppress("unused") location: String?,
        day: ZonedDateTime?,
        latitude: Double,
        longitude: Double
    ) {
        val actual = calculateSunrise(day!!, latitude, longitude)

        assertNotNull(actual)
    }

    @DisplayName("sunrise exists")
    @ParameterizedTest(name = "{index}. {0} => day={1}, latitude={2}, longitude={3}, sunrise={4}")
    @CsvFileSource(numLinesToSkip = 1, resources = ["sunrise-sunet.csv"])
    fun testSunriseIsExact(
        @Suppress("unused") location: String?,
        day: ZonedDateTime?,
        latitude: Double,
        longitude: Double,
        sunrise: ZonedDateTime
    ) {
        val actual = calculateSunrise(day!!, latitude, longitude)

        assertEquals(sunrise, actual)
    }
}
