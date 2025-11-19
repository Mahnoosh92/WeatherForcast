package com.mahnoosh.data.repository.weather

import com.mahnoosh.model.data.CurrentWeather

interface WeatherRepository {
    suspend fun fetchCurrentWeather(
        q: String
    ): Result<CurrentWeather>
}