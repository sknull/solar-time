//[solar-time](../../../index.md)/[de.visualdigits.solartime.control](../index.md)/[CalculatorUtil](index.md)/[toGregorianDate](to-gregorian-date.md)

# toGregorianDate

[JVM]\
fun [toGregorianDate](to-gregorian-date.md)(julianDate: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html)

Convert a Julian date to a Gregorian date. The Gregorian date will be in the local time zone. Accuracy is to the second. <br></br> This is based on the Wikipedia article for Julian day.

#### Return

a Gregorian date in the local time zone.

#### Parameters

JVM

| | |
|---|---|
| julianDate | The date to convert |

#### See also

| | |
|---|---|
|  | [Converting from Julian day to Gregorian date, on Wikipedia](http://en.wikipedia.org/wiki/Julian_day.Gregorian_calendar_from_Julian_day_number) |
