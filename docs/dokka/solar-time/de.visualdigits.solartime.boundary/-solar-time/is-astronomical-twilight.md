//[solar-time](../../../index.md)/[de.visualdigits.solartime.boundary](../index.md)/[SolarTime](index.md)/[isAstronomicalTwilight](is-astronomical-twilight.md)

# isAstronomicalTwilight

[JVM]\
fun [isAstronomicalTwilight](is-astronomical-twilight.md)(calendar: [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html), latitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

#### Return

true if it is astronomical twilight at the given location and the given calendar. This returns true if the given time at the location is between nautical and astronomical twilight dusk or between astronomical and nautical twilight dawn.

#### Parameters

JVM

| | |
|---|---|
| calendar | the datetime for which to determine if it's astronomical twilight in the given location |
| latitude | the latitude of the location in degrees. |
| longitude | the longitude of the location in degrees (West is negative) |
