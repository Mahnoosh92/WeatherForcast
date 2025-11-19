package com.mahnoosh.network.data.datasource.currennt

import com.mahnoosh.network.data.model.CurrentWeatherDTO

interface CurrentWeatherRemoteDatasource {
    suspend fun getCurrentWeather(q: String): Result<CurrentWeatherDTO>
}