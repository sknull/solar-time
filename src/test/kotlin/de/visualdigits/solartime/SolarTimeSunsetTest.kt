package de.visualdigits.solartime

import de.visualdigits.solartime.SolarTime.calculateSunset
import de.visualdigits.solartime.util.CalculatorUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import java.time.ZonedDateTime

class SolarTimeSunsetTest {

    protected val calculatorUtil = CalculatorUtil()

    @DisplayName("sunset exists")
    @ParameterizedTest(name = "{index}. {0} => day={1}, latitude={2}, longitude={3}")
    @CsvFileSource(numLinesToSkip = 1, resources = ["sunrise-sunet.csv"])
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
    @CsvFileSource(numLinesToSkip = 1, resources = ["sunrise-sunet.csv"])
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
