package com.mahnoosh.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentForcastDTO(
    @SerialName("location") val location: LocationDTO = LocationDTO(),
    @SerialName("current") val current: CurrentDTO = CurrentDTO(),
    @SerialName("forecast") val forecast: ForecastDTO = ForecastDTO()
)