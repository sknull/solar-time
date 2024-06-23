package de.visualdigits.solartime.boundary

import de.visualdigits.solartime.boundary.SolarTime.calculateNauticalDusk
import de.visualdigits.solartime.boundary.SolarTime.isNauticalTwilight
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class SunStateCheckerNauticalTwilightTest {
    @Test
    @DisplayName("At 2019-06-24T3:16:00+02:00[Europe/Berlin] is Nautical Twilight in Europe")
    fun inEarly() {
        val day = ZonedDateTime.of(2019, 6, 24, 3, 16, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        val actual = isNauticalTwilight(day, latitude, longitude)

        Assertions.assertThat(actual)
            .isTrue()
    }

    @Test
    @DisplayName("At 2019-06-24T23:55:00+02:00[Europe/Berlin] is Nautical Twilight in Europe")
    fun inLater() {
        val day = ZonedDateTime.of(2019, 6, 24, 23, 55, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        calculateNauticalDusk(day, latitude, longitude)
            ?.also { x: ZonedDateTime? -> println(x) }
        val actual = isNauticalTwilight(day, latitude, longitude)

        assertTrue(actual)
    }

    @Test
    @DisplayName("At 2019-06-24T03:00:00+02:00[Europe/Berlin] is no Nautical Twilight in Europe")
    fun before() {
        val day = ZonedDateTime.of(2019, 6, 24, 3, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        calculateNauticalDusk(day, latitude, longitude)
            ?.also { x: ZonedDateTime? -> println(x) }
        val actual = isNauticalTwilight(day, latitude, longitude)

        assertFalse(actual)
    }

    @Test
    @DisplayName("At 2019-06-24T12:00:00+02:00[Europe/Berlin] is no Nautical Twilight in Europe")
    fun betweenBothTimeSpans() {
        val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        calculateNauticalDusk(day, latitude, longitude)
            ?.also { x: ZonedDateTime? -> println(x) }
        val actual = isNauticalTwilight(day, latitude, longitude)

        assertFalse(actual)
    }

    @Test
    @DisplayName("At 2019-06-24T23:55:00+02:00[Europe/Berlin] is no Nautical Twilight in Europe")
    fun after() {
        val day = ZonedDateTime.of(2019, 6, 24, 23, 59, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 51.449680
        val longitude = 6.973370

        calculateNauticalDusk(day, latitude, longitude)
            ?.also { x: ZonedDateTime? -> println(x) }
        val actual = isNauticalTwilight(day, latitude, longitude)

        assertFalse(actual)
    }
}
