package com.mahnoosh.model.data


data class CurrentForcast(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)