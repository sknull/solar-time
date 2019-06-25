package de.thatsich.solartime.boundary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

class SolarTimeNextSolarMidnightTest {
    @Test
    void solarMidnightIsNotPresent() {
        final var solarTime = new API().getSolarTime();

        final var day = ZonedDateTime.of(2019, 6, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"));
        final var latitude = 51.449680;
        final var longitude = 6.973370;

        final var actual = solarTime.calculateNextSolarMidnight(day, latitude, longitude);

        Assertions.assertThat(actual)
                .isNotPresent();
    }

    @Test
    void solarMidnightIsPresent() {
        final var solarTime = new API().getSolarTime();

        final var day = ZonedDateTime.of(2019, 1, 24, 12, 0, 0, 0, ZoneId.of("Europe/Berlin"));
        final var latitude = 51.449680;
        final var longitude = 6.973370;

        final var actual = solarTime.calculateNextSolarMidnight(day, latitude, longitude);

        Assertions.assertThat(actual)
                .isPresent();
    }
}