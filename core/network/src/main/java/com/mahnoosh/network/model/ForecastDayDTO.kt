package com.mahnoosh.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayDTO(
    @SerialName("date") val date: String? = null,
    @SerialName("date_epoch") val dateEpoch: Int? = null,
    @SerialName("day") val day: DayDTO? = DayDTO(),
    @SerialName("astro") val astro: AstroDTO? = AstroDTO(),
    @SerialName("hour") val hour: List<HourDTO> = listOf()
)