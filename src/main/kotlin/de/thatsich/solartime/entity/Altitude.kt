package de.thatsich.solartime.entity

enum class Altitude(val value: Double) {
    /**
     * The altitude of the sun (solar elevation angle) at the moment of sunrise or sunset: -0.833
     */
    SUNRISE_SUNSET(-0.833),

    /**
     * The altitude of the sun (solar elevation angle) at the moment of civil twilight: -6.0
     */
    CIVIL(-6.0),

    /**
     * The altitude of the sun (solar elevation angle) at the moment of nautical twilight: -12.0
     */
    NAUTICAL(-12.0),

    /**
     * The altitude of the sun (solar elevation angle) at the moment of astronomical twilight: -18.0
     */
    ASTRONOMICAL(-18.0)
}
