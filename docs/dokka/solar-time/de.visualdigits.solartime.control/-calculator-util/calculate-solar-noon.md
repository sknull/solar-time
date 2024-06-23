//[solar-time](../../../index.md)/[de.visualdigits.solartime.control](../index.md)/[CalculatorUtil](index.md)/[calculateSolarNoon](calculate-solar-noon.md)

# calculateSolarNoon

[JVM]\
fun [calculateSolarNoon](calculate-solar-noon.md)(day: [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html), latitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html)?

Calculate the solar noon time for the given date and given location. This is based on the Wikipedia article on the Sunrise equation.

#### Return

a Calendar with the time set to solar noon for the given day.

#### Parameters

JVM

| | |
|---|---|
| day | The day for which to calculate sunrise and sunset |
| latitude | the latitude of the location in degrees. |
| longitude | the longitude of the location in degrees (West is negative) |

#### See also

| | |
|---|---|
|  | [Sunrise equation on Wikipedia](http://en.wikipedia.org/wiki/Sunrise_equation) |
