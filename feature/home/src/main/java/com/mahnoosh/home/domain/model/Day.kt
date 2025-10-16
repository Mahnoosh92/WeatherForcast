package com.mahnoosh.home.domain.model


data class Day(
    val maxtempC: Double,
    val maxtempF: Int,
    val mintempC: Double,
    val mintempF: Double,
    val avgtempC: Double,
    val avgtempF: Double,
    val maxwindMph: Double,
    val maxwindKph: Double,
    val totalprecipMm: Int,
    val totalprecipIn: Int,
    val totalsnowCm: Int,
    val avgvisKm: Int,
    val avgvisMiles: Int,
    val avghumidity: Int,
    val dailyWillItRain: Int,
    val dailyChanceOfRain: Int,
    val dailyWillItSnow: Int,
    val dailyChanceOfSnow: Int,
    val condition: Condition,
    val uv: Double
)