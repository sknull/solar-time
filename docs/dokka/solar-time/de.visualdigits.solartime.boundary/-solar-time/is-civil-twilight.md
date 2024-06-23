//[solar-time](../../../index.md)/[de.visualdigits.solartime.boundary](../index.md)/[SolarTime](index.md)/[isCivilTwilight](is-civil-twilight.md)

# isCivilTwilight

[JVM]\
fun [isCivilTwilight](is-civil-twilight.md)(calendar: [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html), latitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

#### Return

true if it is civil twilight at the given location and the given calendar. This returns true if the given time at the location is between sunset and civil twilight dusk or between civil twilight dawn and sunrise.

#### Parameters

JVM

| | |
|---|---|
| calendar | the datetime for which to determine if it's civil twilight in the given location |
| latitude | the latitude of the location in degrees. |
| longitude | the longitude of the location in degrees (West is negative) |
