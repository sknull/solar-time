//[solar-time](../../../index.md)/[de.visualdigits.solartime.boundary](../index.md)/[SolarTime](index.md)/[calculateCivilDawn](calculate-civil-dawn.md)

# calculateCivilDawn

[JVM]\
fun [calculateCivilDawn](calculate-civil-dawn.md)(day: [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html), latitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html)?

Calculate the civil twilight time for the given date and given location.

#### Return

civil dawn or empty if there is no civil twilight (e.g. no twilight in Antarctica in December)

#### Parameters

JVM

| | |
|---|---|
| day | The day for which to calculate civil twilight |
| latitude | the latitude of the location in degrees. |
| longitude | the longitude of the location in degrees (West is negative) |
