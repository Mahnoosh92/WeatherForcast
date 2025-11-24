package com.mahnoosh.network.data.builder

import com.mahnoosh.network.data.model.CurrentForcastDTO

internal class CurrentForcastDTOBuilder {
    private var locationBuilder: LocationDTOBuilder = LocationDTOBuilder()
    private var currentBuilder: CurrentDTOBuilder = CurrentDTOBuilder()
    private var forecastBuilder: ForecastDTOBuilder = ForecastDTOBuilder()

    // Allows setting the location via a lambda builder
    fun withLocation(block: LocationDTOBuilder.() -> Unit) =
        apply { this.locationBuilder.apply(block) }

    // Allows setting the current weather via a lambda builder
    fun withCurrent(block: CurrentDTOBuilder.() -> Unit) =
        apply { this.currentBuilder.apply(block) }

    // Allows setting the forecast data via a lambda builder
    fun withForecast(block: ForecastDTOBuilder.() -> Unit) =
        apply { this.forecastBuilder.apply(block) }

    fun build(): CurrentForcastDTO {
        return CurrentForcastDTO(
            location = locationBuilder.build(),
            current = currentBuilder.build(),
            forecast = forecastBuilder.build()
        )
    }
}