package de.visualdigits.solartime

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

@Disabled("only for local testing")
class SolarTimeTest {

    private val now = ZonedDateTime.now()

    // Coordinates of Hamburg, Germany
    private val lat = 53.551
    private val lon = 9.994

    @Test
    fun test() {
        println("Astronomical dawn: ${SolarTime.calculateAstronomicalDawn(now, lat, lon)}")
        println("Nautical dawn    : ${SolarTime.calculateNauticalDawn(now, lat, lon)}")
        println("Civil dawn       : ${SolarTime.calculateCivilDawn(now, lat, lon)}")
        println("Lights off       : ${SolarTime.calculateLightsOff(now, lat, lon)}")
        println("Sunrise          : ${SolarTime.calculateSunrise(now, lat, lon)}")
        println("Solar noon       : ${SolarTime.calculateSolarNoon(now, lat, lon)}")
        println("Sunset           : ${SolarTime.calculateSunset(now, lat, lon)}")
        println("Lights on        : ${SolarTime.calculateLightsOn(now, lat, lon)}")
        println("Civil dusk       : ${SolarTime.calculateCivilDusk(now, lat, lon)}")
        println("Nautical dusk    : ${SolarTime.calculateNauticalDusk(now, lat, lon)}")
        println("Astronomical dusk: ${SolarTime.calculateAstronomicalDusk(now, lat, lon)}")
        println("Lights on        ? ${SolarTime.switchLightsOn(ZonedDateTime.of(2024,7,6,22,12,0,0, ZoneId.systemDefault()), lat, lon)}")
    }
}
