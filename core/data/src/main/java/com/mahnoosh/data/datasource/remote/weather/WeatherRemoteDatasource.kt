package com.mahnoosh.data.datasource.remote.weather

import com.mahnoosh.model.data.CurrentWeather

interface WeatherRemoteDatasource {

    suspend fun fetchCurrentWeather(
        q: String
    ): Result<CurrentWeather>
}