package com.mahnoosh.network.model

import com.google.gson.annotations.SerializedName

data class ForecastDTO(
    @SerializedName("forecastday") val forecastday: List<ForecastDayDTO?>?
)