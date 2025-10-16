package com.mahnoosh.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ForecastDTO(
    @SerialName("forecastday") val forecastday: List<ForecastDayDTO> = listOf()
)