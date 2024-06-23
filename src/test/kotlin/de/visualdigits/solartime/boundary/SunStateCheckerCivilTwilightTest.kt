package de.visualdigits.solartime.boundary

import de.visualdigits.solartime.boundary.SolarTime.isCivilTwilight
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SunStateCheckerCivilTwilightTest {
    @Test
    @DisplayName("At 2019-06-24T05:00:00+02:00[Europe/Berlin] is a Civil Twilight in Europe")
    fun civilTwilight() {
        val day = ZonedDateTime.of(2019, 6, 24, 5, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isCivilTwilight(day, latitude, longitude)

        Assertions.assertThat(actual)
            .isTrue()
    }
}
