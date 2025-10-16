package com.mahnoosh.home.domain.usecase.weather

import com.mahnoosh.home.domain.model.CurrentWeather

interface WeatherUsecase {
    suspend operator fun invoke(q: String): Result<CurrentWeather>
}