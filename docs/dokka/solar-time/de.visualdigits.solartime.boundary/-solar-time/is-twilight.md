//[solar-time](../../../index.md)/[de.visualdigits.solartime.boundary](../index.md)/[SolarTime](index.md)/[isTwilight](is-twilight.md)

# isTwilight

[JVM]\
fun [isTwilight](is-twilight.md)(dateTime: [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html), latitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

#### Return

true if at the given location and dateTime, it is civil, nautical, or astronomical twilight.

#### Parameters

JVM

| | |
|---|---|
| latitude | the latitude of the location in degrees. |
| longitude | the longitude of the location in degrees (West is negative) |
| dateTime | the given datetime to check for twilight |