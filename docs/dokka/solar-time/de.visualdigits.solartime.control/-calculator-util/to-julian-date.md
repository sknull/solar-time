//[solar-time](../../../index.md)/[de.visualdigits.solartime.control](../index.md)/[CalculatorUtil](index.md)/[toJulianDate](to-julian-date.md)

# toJulianDate

[JVM]\
fun [toJulianDate](to-julian-date.md)(gregorianDate: [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)

Convert a Gregorian calendar date to a Julian date. Accuracy is to the second. <br></br> This is based on the Wikipedia article for Julian day.

#### Return

the Julian date for the given Gregorian date.

#### Parameters

JVM

| | |
|---|---|
| gregorianDate | Gregorian date in any time zone. |

#### See also

| | |
|---|---|
|  | [Converting to Julian day number on Wikipedia](http://en.wikipedia.org/wiki/Julian_day.Converting_Julian_or_Gregorian_calendar_date_to_Julian_Day_Number) |
