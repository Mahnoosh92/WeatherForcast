package com.mahnoosh.home.domain.model


data class ForecastDay(
    val date: String,
    val dateEpoch: Int,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>
)