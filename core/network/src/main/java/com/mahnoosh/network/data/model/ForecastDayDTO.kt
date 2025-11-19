package com.mahnoosh.network.data.model

import com.google.gson.annotations.SerializedName

data class ForecastDayDTO(
    @SerializedName("date") val date: String?,
    @SerializedName("date_epoch") val dateEpoch: Int?,
    @SerializedName("day") val day: DayDTO?,
    @SerializedName("astro") val astro: AstroDTO?,
    @SerializedName("hour") val hour: List<HourDTO?>?
)