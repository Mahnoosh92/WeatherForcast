package com.mahnoosh.home.data.datasource.remote.weather

import com.mahnoosh.network.ApiService
import com.mahnoosh.network.model.CurrentWeatherDTO
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DefaultWeatherRemoteDatasource @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : WeatherRemoteDatasource {
    override suspend fun fetchCurrentWeather(q: String): Response<CurrentWeatherDTO> =
        withContext(dispatcher) {
            apiService.fetchCurrentWeather(q = q)
        }
}