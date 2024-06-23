//[solar-time](../../../index.md)/[de.visualdigits.solartime.control](../index.md)/[CalculatorUtil](index.md)/[calculateSolarEquationVariables](calculate-solar-equation-variables.md)

# calculateSolarEquationVariables

[JVM]\
fun [calculateSolarEquationVariables](calculate-solar-equation-variables.md)(day: [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html), longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [SolarEquationVariables](../../de.visualdigits.solartime.entity/-solar-equation-variables/index.md)

Return intermediate variables used for calculating sunrise, sunset, and solar noon.

#### Return

a 2-element array with the ecliptic longitude (lambda) as the first element, and solar transit (jtransit) as the second element

#### Parameters

JVM

| | |
|---|---|
| day | The day for which to calculate the ecliptic longitude and jtransit |
| longitude | the longitude of the location in degrees (West is negative) |

#### See also

| | |
|---|---|
|  | [Sunrise equation on Wikipedia](http://en.wikipedia.org/wiki/Sunrise_equation) |
