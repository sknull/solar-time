# Solar Time
Java Library, Calculate solar times like solar noon, sunrise, sunset, astronomical, nautical and civil dawn/dusks

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

```java
final var api = new API();
final var solarTime = api.getSolarTime();

final var now = ZonedDateTime.now();
final var latitude = 51.449680;
final var longitude = 6.973370;

solarTime.calculateNauticalDawn(now, latitude, longitude)
        .map(dateTime -> dateTime.withZoneSameInstant(ZoneId.systemDefault()))
        .map(ZonedDateTime::toLocalDateTime)
        .ifPresent(time -> LOGGER.info("Nautical Dawn: " + time));
```

> INFO: Nautical Dawn: 2019-06-25T03:15:46

```java
final var sunStateChecker = api.getSunStateChecker();
LOGGER.info("is 24-hour day: " + sunStateChecker.is24HourDayTime(now, latitude, longitude));
```

> INFO: is 24-hour day: false

For more examples check the [wiki](https://github.com/sknull/solar-time/wiki/Examples)
