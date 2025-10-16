package com.mahnoosh.home.domain.model


data class CurrentForcast(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)