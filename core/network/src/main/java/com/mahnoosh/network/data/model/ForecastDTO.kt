package com.mahnoosh.network.data.model

import com.google.gson.annotations.SerializedName

data class ForecastDTO(
    @SerializedName("forecastday") val forecastday: List<ForecastDayDTO?>?
)