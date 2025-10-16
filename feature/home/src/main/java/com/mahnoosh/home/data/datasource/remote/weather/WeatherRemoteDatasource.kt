package com.mahnoosh.home.data.datasource.remote.weather

import com.mahnoosh.network.model.CurrentWeatherDTO
import retrofit2.Response

interface WeatherRemoteDatasource {

    suspend fun fetchCurrentWeather(
        q: String
    ): Response<CurrentWeatherDTO>
}