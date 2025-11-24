package com.mahnoosh.network.data.builder

import com.mahnoosh.network.data.model.CurrentWeatherDTO

internal class CurrentWeatherDTOBuilder {
    private var locationBuilder: LocationDTOBuilder = LocationDTOBuilder()
    private var currentBuilder: CurrentDTOBuilder = CurrentDTOBuilder()

    fun withLocation(block: LocationDTOBuilder.() -> Unit) = apply {
        this.locationBuilder.apply(block)
    }

    fun withCurrent(block: CurrentDTOBuilder.() -> Unit) = apply {
        this.currentBuilder.apply(block)
    }

    fun build(): CurrentWeatherDTO {
        return CurrentWeatherDTO(
            location = locationBuilder.build(),
            current = currentBuilder.build()
        )
    }
}