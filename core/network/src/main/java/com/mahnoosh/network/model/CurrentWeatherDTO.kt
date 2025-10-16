package com.mahnoosh.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDTO(
    @SerialName("location") val location: LocationDTO? = LocationDTO(),
    @SerialName("current") val current: CurrentDTO? = CurrentDTO()
)