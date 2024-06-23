package de.visualdigits.solartime

import de.visualdigits.solartime.SolarTime.isNight
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

@DisplayName("Is Night")
class SolarTimeCheckerIsNightTest{

    @Test
    @DisplayName("Before dawn is night")
    fun testBeforeDawn() {
        val day = ZonedDateTime.of(2019, 1, 24, 1, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isNight(day, latitude, longitude)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    @DisplayName("After dusk is night")
    fun testAfterDusk() {
        val day = ZonedDateTime.of(2019, 1, 24, 23, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isNight(day, latitude, longitude)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    @DisplayName("While day is no night")
    fun testWhileDay() {
        val day = ZonedDateTime.of(2019, 1, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isNight(day, latitude, longitude)

        Assertions.assertThat(actual).isFalse()
    }
}
