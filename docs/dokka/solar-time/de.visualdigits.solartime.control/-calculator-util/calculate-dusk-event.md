//[solar-time](../../../index.md)/[de.visualdigits.solartime.control](../index.md)/[CalculatorUtil](index.md)/[calculateDuskEvent](calculate-dusk-event.md)

# calculateDuskEvent

[JVM]\
fun [calculateDuskEvent](calculate-dusk-event.md)(day: [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html), latitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), altitude: [Altitude](../../de.visualdigits.solartime.entity/-altitude/index.md)): [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html)?

Calculate the sunrise and sunset times for the given date and given location. This is based on the Wikipedia article on the Sunrise equation.

#### Return

a two-element Gregorian Calendar array. The first element is the sunrise, the second element is the sunset. This will return null if there is no sunrise or sunset. (Ex: no sunrise in Antarctica in June)

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
