package com.mahnoosh.data.repository.weather

import com.mahnoosh.data.datasource.remote.weather.WeatherRemoteDatasource
import com.mahnoosh.model.data.CurrentWeather
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val weatherRemoteDatasource: WeatherRemoteDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : WeatherRepository {
    override suspend fun fetchCurrentWeather(q: String): Result<CurrentWeather> =
        weatherRemoteDatasource.fetchCurrentWeather(q = q)
}