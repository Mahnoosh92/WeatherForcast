package com.mahnoosh.network.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDTO(
    @SerializedName("location") val location: LocationDTO?,
    @SerializedName("current") val current: CurrentDTO?
)