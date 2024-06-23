package de.visualdigits.solartime

import de.visualdigits.solartime.SolarTime.is24HourDayTime
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class SolarTimeChecker24HourDayTest{

    @Test
    fun testisNot24HourDayTime()
        {
                val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
            val latitude = 51.449680
            val longitude = 6.973370

            val actual = is24HourDayTime(day, latitude, longitude)

            Assertions.assertThat(actual).isFalse()
        }

    @Test
    @DisplayName("In Nunavut, Canada there is a 24h Day")
    fun testIs24HourDayTime() {
        val day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"))
        val latitude = 82.481306
        val longitude = -62.239533

        val actual = is24HourDayTime(day, latitude, longitude)

        Assertions.assertThat(actual).isTrue()
    }
}
