package de.visualdigits.solartime

import de.visualdigits.solartime.model.Altitude
import de.visualdigits.solartime.model.JulianConstants
import de.visualdigits.solartime.model.SolarEquationVariables
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

object CalculatorUtil {

    private const val EARTH_MAX_TILT_TOWARDS_SUN = 23.439

    private const val DAYS_PER_4000_YEARS = 146097
    private const val DAYS_PER_CENTURY = 36524
    private const val DAYS_PER_4_YEARS = 1461
    private const val DAYS_PER_5_MONTHS = 153

    /**
     * sunrise
     * civil dawn
     * nautical dawn
     * astronomical dawn
     */
    fun calculateDawnEvent(
        day: ZonedDateTime,
        latitude: Double,
        longitude: Double,
        altitude: Altitude
    ): ZonedDateTime? {
        return calculateJulianSunrise(day, latitude, longitude, altitude)
            ?.let { julianDate: Double -> toGregorianDate(julianDate) }
            ?.let { sunrise: ZonedDateTime? -> shiftDayToZoneOfOtherDay(sunrise, day) }
    }

    /**
     * Calculate the sunrise and sunset times for the given date and given
     * location. This is based on the Wikipedia article on the Sunrise equation.
     *
     * @param day       The day for which to calculate sunrise and sunset
     * @param latitude  the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees (West is negative)
     * @return a two-element Gregorian Calendar array. The first element is the
     * sunrise, the second element is the sunset. This will return null if there is no sunrise or sunset. (Ex: no sunrise in Antarctica in June)
     * @see [Sunrise equation on Wikipedia](http://en.wikipedia.org/wiki/Sunrise_equation)
     */
    fun calculateDuskEvent(
        day: ZonedDateTime,
        latitude: Double,
        longitude: Double,
        altitude: Altitude
    ): ZonedDateTime? {
        return calculateJulianSunset(day, latitude, longitude, altitude)
            ?.let { julianDate: Double -> toGregorianDate(julianDate) }
            ?.let { duskEvent: ZonedDateTime? -> shiftDayToZoneOfOtherDay(duskEvent, day) }
    }

    fun calculateHourAngle(altitude: Altitude, latitudeRad: Double, sunDeclination: Double): Double? {
        val radAltitude = Math.toRadians(altitude.value)
        val omega = acos((sin(radAltitude) - sin(latitudeRad) * sin(sunDeclination)) / (cos(latitudeRad) * cos(sunDeclination)))

        return if (!java.lang.Double.isNaN(omega)) {
            omega
        } else {
            null
        }
    }

    fun is24HourDayTime(latitudeRad: Double, sunDeclination: Double): Boolean {
        return tan(latitudeRad) * tan(sunDeclination) > 1
    }

    fun is24HourNightTime(latitudeRad: Double, sunDeclination: Double): Boolean {
        return tan(latitudeRad) * tan(sunDeclination) < -1
    }

    fun calculateJulianSunrise(
        day: ZonedDateTime,
        latitude: Double,
        longitude: Double,
        altitude: Altitude
    ): Double? {
        val maybeSunset = calculateJulianSunset(day, latitude, longitude, altitude)
        return maybeSunset?.let { sunset: Double ->
            val solarEquationVariables =
                calculateSolarEquationVariables(day, longitude)
            // Sunrise
            val jrise = solarEquationVariables.jtransit - (sunset - solarEquationVariables.jtransit)
            jrise
        }
    }

    fun calculateJulianSunset(
        day: ZonedDateTime,
        latitude: Double,
        longitude: Double,
        altitude: Altitude
    ): Double? {
        val solarEquationVariables = calculateSolarEquationVariables(day, longitude)

        val inverted = -longitude
        val latitudeRad = Math.toRadians(latitude)

        // Hour angle
        val maybeOmega = calculateHourAngle(altitude, latitudeRad, solarEquationVariables.delta)

        return maybeOmega?.let { omega: Double? ->
            // Sunset
            JulianConstants.JULIAN_DATE_2000_01_01 + JulianConstants.CONST_0009 + ((Math.toDegrees(omega!!) + inverted) / JulianConstants.CONST_360 + solarEquationVariables.n + (0.0053 * sin(solarEquationVariables.m)) - 0.0069 * sin(2 * solarEquationVariables.lambda))
        }
    }

    /**
     * Return intermediate variables used for calculating sunrise, sunset, and solar noon.
     *
     * @param day         The day for which to calculate the ecliptic longitude and jtransit
     * @param longitude   the longitude of the location in degrees (West is negative)
     * @return a 2-element array with the ecliptic longitude (lambda) as the first element, and solar transit (jtransit) as the second element
     * @see [Sunrise equation on Wikipedia](http://en.wikipedia.org/wiki/Sunrise_equation)
     */
    fun calculateSolarEquationVariables(day: ZonedDateTime, longitude: Double): SolarEquationVariables {
        var lt = longitude
        lt = -lt

        // Get the given date as a Julian date.
        val julianDate = toJulianDate(day)

        // Calculate current Julian cycle (number of days since 2000-01-01).
        val nstar =
            julianDate - JulianConstants.JULIAN_DATE_2000_01_01 - JulianConstants.CONST_0009 - lt / JulianConstants.CONST_360
        val n = Math.round(nstar).toDouble()

        // Approximate solar noon
        val jstar = JulianConstants.JULIAN_DATE_2000_01_01 + JulianConstants.CONST_0009 + (lt / JulianConstants.CONST_360) + n
        // Solar mean anomaly
        val m = Math
            .toRadians(
                (357.5291 + 0.98560028 * (jstar - JulianConstants.JULIAN_DATE_2000_01_01))
                        % JulianConstants.CONST_360
            )

        // Equation of center
        val c = 1.9148 * sin(m) + 0.0200 * sin(2 * m) + 0.0003 * sin(3 * m)

        // Ecliptic longitude
        val lambda = Math
            .toRadians((Math.toDegrees(m) + 102.9372 + c + 180) % JulianConstants.CONST_360)

        // Solar transit (hour angle for solar noon)
        val jtransit = jstar + 0.0053 * sin(m) - 0.0069 * sin(2 * lambda)

        // Declination of the sun.
        val delta = asin(sin(lambda) * sin(Math.toRadians(EARTH_MAX_TILT_TOWARDS_SUN)))


        return SolarEquationVariables(n, m, lambda, jtransit, delta)
    }

    /**
     * Calculate the solar noon time for the given date and given location.
     * This is based on the Wikipedia article on the Sunrise equation.
     *
     * @param day         The day for which to calculate sunrise and sunset
     * @param latitude  the latitude of the location in degrees.
     * @param longitude   the longitude of the location in degrees (West is negative)
     * @return            a Calendar with the time set to solar noon for the given day.
     * @see [Sunrise equation on Wikipedia](http://en.wikipedia.org/wiki/Sunrise_equation)
     */
    fun calculateSolarNoon(day: ZonedDateTime, latitude: Double, longitude: Double): ZonedDateTime? {
        val solarEquationVariables = calculateSolarEquationVariables(day, longitude)

        // Add a check for Antarctica in June and December (sun always down or up, respectively).
        // In this case, jtransit will be filled in, but we need to check the hour angle omega for
        // sunrise.
        // If there's no sunrise (omega is NaN), there's no solar noon.
        val latitudeRad = Math.toRadians(latitude)

        // Hour angle
        return calculateHourAngle(
            Altitude.SUNRISE_SUNSET,
            latitudeRad,
            solarEquationVariables.delta
        )
            ?.let { omega: Double? ->
                // Convert jtransit Gregorian dates, in UTC
                val localTimeNoon = toGregorianDate(solarEquationVariables.jtransit)
                val zone = day.zone
                val noonWithMovedTimeZone = localTimeNoon!!.withZoneSameInstant(zone)
                noonWithMovedTimeZone
            }
    }

    fun shiftDayToZoneOfOtherDay(toBeShifted: ZonedDateTime?, otherDay: ZonedDateTime): ZonedDateTime {
        val zone = otherDay.zone
        val shifted = toBeShifted!!.withZoneSameInstant(zone)

        return shifted
    }

    /**
     * Convert a Gregorian calendar date to a Julian date. Accuracy is to the
     * second.
     * <br></br>
     * This is based on the Wikipedia article for Julian day.
     *
     * @param gregorianDate Gregorian date in any time zone.
     * @return the Julian date for the given Gregorian date.
     * @see [Converting to Julian day number on Wikipedia](http://en.wikipedia.org/wiki/Julian_day.Converting_Julian_or_Gregorian_calendar_date_to_Julian_Day_Number)
     */
    fun toJulianDate(gregorianDate: ZonedDateTime): Double {
        // Convert the date to the UTC time zone.
        val utc = ZoneOffset.UTC
        val gregorianDateUTC = gregorianDate.withZoneSameInstant(utc)

        // For the year (Y) astronomical year numbering is used, thus 1 BC is 0,
        // 2 BC is -1, and 4713 BC is -4712.
        val year = gregorianDateUTC.year
        // The months (M) January to December are 1 to 12
        val month = gregorianDateUTC.monthValue
        // D is the day of the month.
        val day = gregorianDateUTC.dayOfMonth
        val a = (14 - month) / 12
        val y = year + 4800 - a
        val m = month + 12 * a - 3

        val julianDay = day + (153 * m + 2) / 5 + 365 * y + (y / 4) - (y / 100) + (y / 400) - 32045
        val hour = gregorianDateUTC.hour.toDouble()
        val minute = gregorianDateUTC.minute.toDouble()
        val second = gregorianDateUTC.second.toDouble()

        return julianDay + (hour - 12) / 24 + minute / 1440 + second / 86400
    }

    /**
     * Convert a Julian date to a Gregorian date. The Gregorian date will be in
     * the local time zone. Accuracy is to the second.
     * <br></br>
     * This is based on the Wikipedia article for Julian day.
     *
     * @param julianDate The date to convert
     * @return a Gregorian date in the local time zone.
     * @see [Converting from Julian day to Gregorian date, on Wikipedia](http://en.wikipedia.org/wiki/Julian_day.Gregorian_calendar_from_Julian_day_number)
     */
    fun toGregorianDate(julianDate: Double): ZonedDateTime {
        // this shifts the epoch back by one half day,
        // to start it at 00:00UTC, instead of 12:00 UTC
        val J = (julianDate + 0.5).toInt()

        // this shifts the epoch back to astronomical
        // year -4800 instead of the start of the Christian era in year AD 1 of
        // the proleptic Gregorian calendar
        val j = J + 32044

        val g = j / DAYS_PER_4000_YEARS
        val dg = j % DAYS_PER_4000_YEARS

        val c = ((dg / DAYS_PER_CENTURY + 1) * 3) / 4
        val dc = dg - c * DAYS_PER_CENTURY

        val b = dc / DAYS_PER_4_YEARS
        val db = dc % DAYS_PER_4_YEARS

        val a = ((db / 365 + 1) * 3) / 4
        val da = db - a * 365

        // this is the integer number of full years elapsed since March 1, 4801 BC at 00:00 UTC
        val y = g * 400 + c * 100 + b * 4 + a

        // this is the integer number of full months elapsed since the last March 1 at 00:00 UTC
        val m = (da * 5 + 308) / DAYS_PER_5_MONTHS - 2

        // this is the number of days elapsed since day 1 of the month at 00:00 UTC,
        // including fractions of one day
        val d = da - ((m + 4) * DAYS_PER_5_MONTHS) / 5 + 122

        val year = y - 4800 + (m + 2) / 12
        val month = (m + 2) % 12
        val day = d + 1

        // Apply the fraction of the day in the Julian date to the Gregorian
        // date.
        // Example: dayFraction = 0.717
        val dayFraction = (julianDate + 0.5) - J

        // Ex: 0.717*24 = 17.208 hours. We truncate to 17 hours.
        val hours = (dayFraction * 24).toInt()
        // Ex: 17.208 - 17 = 0.208 days. 0.208*60 = 12.48 minutes. We truncate
        // to 12 minutes.
        val minutes = ((dayFraction * 24 - hours) * 60.0).toInt()
        // Ex: 17.208*60 - (17*60 + 12) = 1032.48 - 1032 = 0.48 minutes. 0.48*60
        // = 28.8 seconds.
        // We round to 29 seconds.
        val seconds = ((dayFraction * 24 * 3600 - (hours * 3600 + minutes * 60)) + .5).toInt()

        // Create the gregorian date in UTC.
        // through the round we can get 60 seconds but this is invalid by the time API
        // thus adding 60 seconds is equivalent to adding one minute
        val gregorianDateUTC = ZonedDateTime.of(year, month + 1, day, hours, minutes, 0, 0, ZoneOffset.UTC)
            .plusSeconds(seconds.toLong())
        val utc = ZoneId.systemDefault()
        val localZoned = gregorianDateUTC.withZoneSameInstant(utc)

        return localZoned
    }
}
