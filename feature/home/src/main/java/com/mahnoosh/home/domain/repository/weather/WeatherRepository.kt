package com.mahnoosh.home.domain.repository.weather

import com.mahnoosh.home.domain.model.CurrentWeather

interface WeatherRepository {
    suspend fun fetchCurrentWeather(
        q: String
    ): Result<CurrentWeather>
}