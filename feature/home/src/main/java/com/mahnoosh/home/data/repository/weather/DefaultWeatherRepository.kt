package com.mahnoosh.home.data.repository.weather

import com.mahnoosh.home.data.datasource.remote.weather.WeatherRemoteDatasource
import com.mahnoosh.home.domain.model.CurrentWeather
import com.mahnoosh.home.domain.repository.weather.WeatherRepository
import com.mahnoosh.network.model.CurrentWeatherDTO
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import toDomain
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val weatherRemoteDatasource: WeatherRemoteDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : WeatherRepository {
    override suspend fun fetchCurrentWeather(q: String): Result<CurrentWeather> =
        withContext(dispatcher) {
            try {
                val response = weatherRemoteDatasource.fetchCurrentWeather(q = q)
                if (response.isSuccessful)
                    Result.success(response.body()?.toDomain() ?: CurrentWeatherDTO().toDomain())
                else
                    Result.failure(Exception(response.errorBody().toString()))
            } catch (e: Exception) {
                Result.failure(Exception(e.message.toString()))
            }
        }
}