package de.thatsich.solartime.boundary

import de.thatsich.solartime.boundary.SolarTime.calculateSunset
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import java.time.ZonedDateTime

internal class SolarTimeSunsetTest {
    @DisplayName("sunset exists")
    @ParameterizedTest(name = "{index}. {0} => day={1}, latitude={2}, longitude={3}")
    @CsvFileSource(numLinesToSkip = 1, resources = ["./sunrise-sunet.csv"])
    fun testSunriseExists(
        @Suppress("unused") location: String?,
        day: ZonedDateTime?,
        latitude: Double,
        longitude: Double
    ) {
        val actual = calculateSunset(day!!, latitude, longitude)

        assertNotNull(actual)
    }

    @DisplayName("sunrise exists")
    @ParameterizedTest(name = "{index}. {0} => day={1}, latitude={2}, longitude={3}, sunrise={4}, sunset={5}")
    @CsvFileSource(numLinesToSkip = 1, resources = ["./sunrise-sunet.csv"])
    fun testSunriseIsExact(
        @Suppress("unused") location: String?,
        day: ZonedDateTime?,
        latitude: Double,
        longitude: Double,
        @Suppress("unused") sunrise: ZonedDateTime?,
        sunset: ZonedDateTime
    ) {
        val actual = calculateSunset(day!!, latitude, longitude)

        assertEquals(sunset, actual)
    }
}