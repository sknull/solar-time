# Solar Time
Kotlin Library, Calculate solar times like solar noon, sunrise, sunset, astronomical, nautical and civil dawn/dusks

---

This work is heavily inspired by https://github.com/caarmen/SunriseSunset. The fundamental changes are:

* requires Java 17+,
* requires Kotlin 1.9+
* complete rework to Kotlin

## Project Status

[![Maintenance](https://img.shields.io/maintenance/yes/2024.svg)](https://github.com/sknull/solar-time)

[![Build Status](https://travis-ci.org/sknull/solar-time.svg?branch=main)](https://travis-ci.org/sknull/solar-time)

[![Codecov Coverage](https://codecov.io/gh/sknull/solar-time/branch/master/graph/badge.svg)](https://codecov.io/gh/sknull/solar-time)
[![Coverage Status](https://coveralls.io/repos/github/sknull/solar-time/badge.svg?branch=master&kill_cache=1)](https://coveralls.io/github/sknull/solar-time?branch=master)

[![Maintainability](https://api.codeclimate.com/v1/badges/7bebabb71ca31cf2aa8d/maintainability)](https://codeclimate.com/github/sknull/solar-time/maintainability)

## Example Usage

Entrypoint is the `API`. You can instatiate it via `new API()` to retrieve the `SolarTime` and `SunStateChecker` endpoints.

```kotlin

val now = ZonedDateTime.now();
val latitude = 51.449680;
val longitude = 6.973370;

SolarTime.calculateNauticalDawn(now, latitude, longitude)
        ?.map { it.withZoneSameInstant(ZoneId.systemDefault()) }
        ?.map { it.toLocalDateTime }
        ?.also {println("Nautical Dawn: $it") }
```

> INFO: Nautical Dawn: 2019-06-25T03:15:46

```kotlin
val sunStateChecker = SolarTime.getSunStateChecker();
println("is 24-hour day: ${sunStateChecker.is24HourDayTime(now, latitude, longitude)}")
```

> INFO: is 24-hour day: false

For more examples check the [wiki](https://github.com/sknull/solar-time/wiki/Examples)
