package com.mahnoosh.network.data.model

import com.google.gson.annotations.SerializedName

data class CurrentForcastDTO(
    @SerializedName("location") val location: LocationDTO?,
    @SerializedName("current") val current: CurrentDTO?,
    @SerializedName("forecast") val forecast: ForecastDTO?
)