package com.mahnoosh.home.domain.usecase.weather

import com.mahnoosh.home.domain.model.CurrentWeather
import com.mahnoosh.home.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class DefaultWeatherUsecase @Inject constructor(private val weatherRepository: WeatherRepository) :
    WeatherUsecase {
    override suspend fun invoke(q: String): Result<CurrentWeather> =
        weatherRepository.fetchCurrentWeather(q = q)
}