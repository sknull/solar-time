package de.visualdigits.solartime

import de.visualdigits.solartime.SolarTime.isTwilight
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class SolarTimeCheckerTwilightTest {

    @Test
    @DisplayName("At 2019-01-24T12:00:00+02:00[Europe/Berlin] is no Twilight in Europe")
    fun testnoTwilight() {
        val day = ZonedDateTime.of(2019, 1, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isTwilight(day, latitude, longitude)

        Assertions.assertThat(actual).isFalse()
    }

    @Test
    @DisplayName("At 2019-01-24T06:30:00+02:00[Europe/Berlin] is Astronomical Twilight in Europe")
    fun testastronomicalTwilightIsTwilight() {
        val day = ZonedDateTime.of(2019, 1, 24, 6, 30, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isTwilight(day, latitude, longitude)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    @DisplayName("At 2019-06-24T03:16:00+02:00[Europe/Berlin] is Nautical Twilight in Europe")
    fun testnauticalTwilightIsTwilight() {
        val day = ZonedDateTime.of(2019, 6, 24, 3, 16, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isTwilight(day, latitude, longitude)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    @DisplayName("At 2019-06-24T05:00:00+02:00[Europe/Berlin] is a Civil Twilight in Europe")
    fun testcivilTwilightIsTwilight() {
        val day = ZonedDateTime.of(2019, 6, 24, 5, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isTwilight(day, latitude, longitude)

        Assertions.assertThat(actual).isTrue()
    }
}
