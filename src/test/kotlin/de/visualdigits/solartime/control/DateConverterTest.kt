package de.visualdigits.solartime.control

import de.visualdigits.solartime.CalculatorUtil.toGregorianDate
import de.visualdigits.solartime.CalculatorUtil.toJulianDate
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

@DisplayName("Test conversion between gregorian and julian date")
internal class DateConverterTest {
    
    @DisplayName("gregorian to julian date conversion")
    @ParameterizedTest(name = "{index} => gregorian={0}, expected={1}")
    @CsvFileSource(numLinesToSkip = 1, resources = ["./gregorian-to-julian-date.csv"])
    fun testTJulianDate(gregorian: ZonedDateTime?, expected: Double) {

        val actual = toJulianDate(gregorian!!)

        Assertions.assertThat(actual)
            .isEqualTo(expected)
    }

    @DisplayName("julian to gregorian conversion")
    @ParameterizedTest(name = "{index} => expected={0}, julian={1}")
    @CsvFileSource(numLinesToSkip = 1, resources = ["./gregorian-to-julian-date.csv"])
    fun testTGregorianDate(expected: ZonedDateTime?, julian: Double) {
        val actual = toGregorianDate(julian)
            .toInstant()
            .atZone(ZoneId.systemDefault())

        Assertions.assertThat(actual)
            .isEqualTo(expected)
    }

    @DisplayName("Chained julian to gregorian. Should result in the same")
    @ParameterizedTest(name = "{index} => gregorian={0}")
    @CsvFileSource(numLinesToSkip = 1, resources = ["./gregorian-to-julian-date.csv"])
    fun chaingedGregorianToJulian(gregorian: ZonedDateTime?) {
        val julian = toJulianDate(gregorian!!)
        val actualGregorian = toGregorianDate(julian)
            .toInstant()
            .atZone(ZoneOffset.UTC)

        Assertions.assertThat(actualGregorian)
            .isEqualTo(gregorian)
    }

    @DisplayName("Chained gregorian to julian. Should result in the same")
    @ParameterizedTest(name = "{index} => gregorian={0}, expected={1}")
    @CsvFileSource(numLinesToSkip = 1, resources = ["./gregorian-to-julian-date.csv"])
    fun chaingedJulianToGregorian(@Suppress("unused") ignored: ZonedDateTime?, julian: Double) {
        val gregorian = toGregorianDate(julian)
        val actualJulian = toJulianDate(gregorian)

        Assertions.assertThat(actualJulian)
            .isEqualTo(julian)
    }
}
