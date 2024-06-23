package de.visualdigits.solartime.boundary

import de.visualdigits.solartime.control.CalculatorUtil
import de.visualdigits.solartime.control.CalculatorUtil.calculateDawnEvent
import de.visualdigits.solartime.control.CalculatorUtil.calculateDuskEvent
import de.visualdigits.solartime.control.CalculatorUtil.calculateSolarEquationVariables
import de.visualdigits.solartime.entity.Altitude
import de.visualdigits.solartime.entity.DayPeriod
import de.visualdigits.solartime.entity.TimeSpan
import java.time.Instant
import java.time.ZonedDateTime
import kotlin.math.tan

/**
 * Provides methods to determine the sunrise, sunset, civil twilight,
 * nautical twilight, and astronomical twilight times of a given
 * location, or if it is currently day or night at a given location. <br></br>
 * Also provides methods to convert between Gregorian and Julian dates.<br></br>
 * The formulas used by this class are from the Wikipedia articles on Julian Day
 * and Sunrise Equation. <br></br>
 *
 * @see [Julian Day on Wikipedia](http://en.wikipedia.org/wiki/Julian_day)
 *
 * @see [Sunrise equation on Wikipedia](http://en.wikipedia.org/wiki/Sunrise_equation)
 */
object SolarTime {

    fun calculatePreviousSolarMidnight(
        day: ZonedDateTime,
        latitude: Double,
        longitude: Double
    ): ZonedDateTime? {
        val previousDay = day.minusDays(1)

        return calculateAstronomicalDusk(previousDay, latitude, longitude)
            ?.let { dusk: ZonedDateTime? ->
                calculateAstronomicalDawn(day, latitude, longitude)
                    ?.let { dawn: ZonedDateTime? -> calculateMidpoint(dusk, dawn) }
            }
    }

    /**
     * Calculate the astronomical twilight time for the given date and given location.
     *
     * @param day       The day for which to calculate astronomical twilight
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     *
     * @return astronomical dawn or empty if there is no astronomical twilight (e.g. no twilight in Antarctica in December)
     */
    fun calculateAstronomicalDawn(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        return calculateDawnEvent(day, latitude, longitude, Altitude.ASTRONOMICAL)
    }

    /**
     * Calculate the nautical twilight time for the given date and given location.
     *
     * @param day       The day for which to calculate nautical twilight
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     *
     * @return nautical dawn or empty if there is no nautical twilight (e.g. no twilight in Antarctica in December)
     */
    fun calculateNauticalDawn(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        return calculateDawnEvent(day, latitude, longitude, Altitude.NAUTICAL)
    }

    /**
     * Calculate the civil twilight time for the given date and given location.
     *
     * @param day       The day for which to calculate civil twilight
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     *
     * @return civil dawn or empty if there is no civil twilight (e.g. no twilight in Antarctica in December)
     */
    fun calculateCivilDawn(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        return calculateDawnEvent(day, latitude, longitude, Altitude.CIVIL)
    }

    fun calculateSunrise(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        return calculateDawnEvent(day, latitude, longitude, Altitude.SUNRISE_SUNSET)
    }

    fun calculateSolarNoon(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        return CalculatorUtil.calculateSolarNoon(day, latitude, longitude)
    }

    fun calculateSunset(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        return calculateDuskEvent(day, latitude, longitude, Altitude.SUNRISE_SUNSET)
    }

    /**
     * Calculate the civil twilight time for the given date and given location.
     *
     * @param day       The day for which to calculate civil twilight
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     *
     * @return civil dusk or empty if there is no civil twilight (e.g. no twilight in Antarctica in December)
     */
    fun calculateCivilDusk(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        return calculateDuskEvent(day, latitude, longitude, Altitude.CIVIL)
    }

    /**
     * Calculate the nautical twilight time for the given date and given location.
     *
     * @param day       The day for which to calculate nautical twilight
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     *
     * @return nautical dusk or empty if there is no nautical twilight (e.g. no twilight in Antarctica in December)
     */
    fun calculateNauticalDusk(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        return calculateDuskEvent(day, latitude, longitude, Altitude.NAUTICAL)
    }

    /**
     * Calculate the astronomical twilight time for the given date and given location.
     *
     * @param day       The day for which to calculate astronomical twilight
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     *
     * @return astronomical dusk or empty if there is no astronomical twilight (e.g. no twilight in Antarctica in December)
     */
    fun calculateAstronomicalDusk(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        return calculateDuskEvent(day, latitude, longitude, Altitude.ASTRONOMICAL)
    }

    fun calculateNextSolarMidnight(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        val nextDay = day.plusDays(1)

        return calculateAstronomicalDusk(day, latitude, longitude)
            ?.let { dusk: ZonedDateTime? ->
                calculateAstronomicalDawn(nextDay, latitude, longitude)
                    ?.let { dawn: ZonedDateTime? -> calculateMidpoint(dusk, dawn) }
            }
    }

    /**
     * @param dateTime  a datetime
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     * @return true if it is day at the given location and given datetime. This returns
     * true if the given datetime at the location is after the sunrise and
     * before the sunset for that location.
     */
    fun isDay(dateTime: ZonedDateTime, latitude: Double, longitude: Double): Boolean {
        return calculateSunrise(dateTime, latitude, longitude)
            ?.let { sunrise: ZonedDateTime? ->
                calculateSunset(dateTime, latitude, longitude)
                    ?.let { sunset: ZonedDateTime? -> dateTime.isAfter(sunrise) && dateTime.isBefore(sunset) }
            }
            ?: is24HourDayTime(dateTime, latitude, longitude)
    }

    /**
     * @param calendar  a datetime
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     * @return true if it is night at the given location and datetime. This returns
     * true if the given datetime at the location is after the astronomical twilight dusk and before
     * the astronomical twilight dawn.
     */
    fun isNight(calendar: ZonedDateTime, latitude: Double, longitude: Double): Boolean {
        return calculateAstronomicalDawn(calendar, latitude, longitude)
            ?.let { dawn: ZonedDateTime? ->
                calculateAstronomicalDusk(calendar, latitude, longitude)
                    ?.let { dusk: ZonedDateTime? -> calendar.isBefore(dawn) || calendar.isAfter(dusk) }
            } ?: is24HourNightTime(calendar, latitude, longitude)
    }


    /**
     * @param calendar the datetime for which to determine if it's civil twilight in the given location
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     * @return true if it is civil twilight at the given location and the given calendar.
     * This returns true if the given time at the location is between sunset and civil twilight dusk
     * or between civil twilight dawn and sunrise.
     */
    fun isCivilTwilight(calendar: ZonedDateTime, latitude: Double, longitude: Double): Boolean {
        return calculateSunset(calendar, latitude, longitude)
            ?.let { sunset: ZonedDateTime? ->
                calculateSunrise(calendar, latitude, longitude)
                    ?.let { sunrise: ZonedDateTime? ->
                        calculateCivilDusk(calendar, latitude, longitude)
                            ?.let { civilDusk: ZonedDateTime? ->
                                calculateCivilDawn(calendar, latitude, longitude)
                                    ?.let { civilDawn: ZonedDateTime? ->
                                        inBetween(
                                            calendar,
                                            TimeSpan(civilDawn, sunrise),
                                            TimeSpan(sunset, civilDusk)
                                        )
                                    }
                            }
                    }
            } ?: false
    }

    /**
     * @param calendar the datetime for which to determine if it's nautical twilight in the given location
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     * @return true if it is nautical twilight at the given location and the given calendar.
     * This returns true if the given time at the location is between civil and nautical twilight dusk
     * or between nautical and civil twilight dawn.
     */
    fun isNauticalTwilight(calendar: ZonedDateTime, latitude: Double, longitude: Double): Boolean {
        return calculateNauticalDawn(calendar, latitude, longitude)
            ?.let { nauticalDawn: ZonedDateTime? ->
                calculateNauticalDusk(calendar, latitude, longitude)
                    ?.let { nauticalDusk: ZonedDateTime? ->
                        calculateCivilDusk(calendar, latitude, longitude)
                            ?.let { civilDusk: ZonedDateTime? ->
                                calculateCivilDawn(calendar, latitude, longitude)
                                    ?.let { civilDawn: ZonedDateTime? ->
                                        inBetween(
                                            calendar,
                                            TimeSpan(nauticalDawn, civilDawn),
                                            TimeSpan(civilDusk, nauticalDusk)
                                        )
                                    }
                            }
                    }
            } ?: false
    }

    /**
     * @param calendar the datetime for which to determine if it's astronomical twilight in the given location
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     * @return true if it is astronomical twilight at the given location and the given calendar.
     * This returns true if the given time at the location is between nautical and astronomical twilight dusk
     * or between astronomical and nautical twilight dawn.
     */
    fun isAstronomicalTwilight(calendar: ZonedDateTime, latitude: Double, longitude: Double): Boolean {
        return calculateNauticalDawn(calendar, latitude, longitude)
            ?.let { nauticalDawn: ZonedDateTime? ->
                calculateNauticalDusk(calendar, latitude, longitude)
                    ?.let { nauticalDusk: ZonedDateTime? ->
                        calculateAstronomicalDusk(calendar, latitude, longitude)
                            ?.let { astronomicalDusk: ZonedDateTime? ->
                                calculateAstronomicalDawn(calendar, latitude, longitude)
                                    ?.let { astronomicalDawn: ZonedDateTime? ->
                                        inBetween(
                                            calendar,
                                            TimeSpan(astronomicalDawn, nauticalDawn),
                                            TimeSpan(nauticalDusk, astronomicalDusk)
                                        )
                                    }
                            }
                    }
            } ?: false
    }

    /**
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     * @param dateTime the given datetime to check for twilight
     * @return true if at the given location and dateTime, it is civil, nautical, or astronomical twilight.
     */
    fun isTwilight(dateTime: ZonedDateTime, latitude: Double, longitude: Double): Boolean {
        return (isCivilTwilight(dateTime, latitude, longitude)
                || isNauticalTwilight(dateTime, latitude, longitude)
                || isAstronomicalTwilight(dateTime, latitude, longitude))
    }

    fun getDayPeriod(dateTime: ZonedDateTime, latitude: Double, longitude: Double): DayPeriod {
        val period = if (isDay(dateTime, latitude, longitude)) {
            DayPeriod.DAY
        } else if (isCivilTwilight(dateTime, latitude, longitude)) {
            DayPeriod.CIVIL_TWILIGHT
        } else if (isNauticalTwilight(dateTime, latitude, longitude)) {
            DayPeriod.NAUTICAL_TWILIGHT
        } else if (isAstronomicalTwilight(dateTime, latitude, longitude)) {
            DayPeriod.ASTRONOMICAL_TWILIGHT
        } else {
            // no need to call isNight here if this is the default case
            DayPeriod.NIGHT
        }

        return period
    }

    fun is24HourDayTime(day: ZonedDateTime, latitude: Double, longitude: Double): Boolean {
        val solarEquationVariables = calculateSolarEquationVariables(day, longitude)
        val sunDeclination = solarEquationVariables.delta
        val rads = Math.toRadians(latitude)

        return tan(rads) * tan(sunDeclination) > 1
    }

    fun is24HourNightTime(day: ZonedDateTime, latitude: Double, longitude: Double): Boolean {
        val solarEquationVariables = calculateSolarEquationVariables(day, longitude)
        val sunDeclination = solarEquationVariables.delta
        val rads = Math.toRadians(latitude)

        return tan(rads) * tan(sunDeclination) < -1
    }

    private fun inBetween(now: ZonedDateTime, early: TimeSpan, late: TimeSpan): Boolean {
        val inEarly = now.isAfter(early.earlier) && now.isBefore(early.later)
        val inLater = now.isAfter(late.earlier) && now.isBefore(late.later)

        return inEarly || inLater
    }

    private fun calculateMidpoint(first: ZonedDateTime?, second: ZonedDateTime?): ZonedDateTime {
        val midpointInMillis = (first!!.toInstant().toEpochMilli() + second!!.toInstant().toEpochMilli()) / 2

        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(midpointInMillis), first.zone)
    }
}
