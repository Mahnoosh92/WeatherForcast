package com.mahnoosh.network.data.builder

import com.mahnoosh.network.data.model.AstroDTO

internal class AstroDTOBuilder {
    private var sunrise: String? = "06:00 AM"
    private var sunset: String? = "06:00 PM"
    private var moonrise: String? = "07:00 AM"
    private var moonset: String? = "07:00 PM"
    private var moonPhase: String? = "Full Moon"
    private var moonIllumination: Int? = 100
    private var isMoonUp: Int? = 1
    private var isSunUp: Int? = 0

    fun withSunrise(sunrise: String?) = apply { this.sunrise = sunrise }
    fun withSunset(sunset: String?) = apply { this.sunset = sunset }
    fun withMoonrise(moonrise: String?) = apply { this.moonrise = moonrise }
    fun withMoonset(moonset: String?) = apply { this.moonset = moonset }
    fun withMoonPhase(moonPhase: String?) = apply { this.moonPhase = moonPhase }
    fun withMoonIllumination(moonIllumination: Int?) = apply { this.moonIllumination = moonIllumination }
    fun withIsMoonUp(isMoonUp: Int?) = apply { this.isMoonUp = isMoonUp }
    fun withIsSunUp(isSunUp: Int?) = apply { this.isSunUp = isSunUp }

    fun build(): AstroDTO {
        return AstroDTO(
            sunrise = sunrise,
            sunset = sunset,
            moonrise = moonrise,
            moonset = moonset,
            moonPhase = moonPhase,
            moonIllumination = moonIllumination,
            isMoonUp = isMoonUp,
            isSunUp = isSunUp
        )
    }
}