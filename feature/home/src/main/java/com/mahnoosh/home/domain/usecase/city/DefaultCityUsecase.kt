package com.mahnoosh.home.domain.usecase.city

import com.mahnoosh.home.domain.model.City
import com.mahnoosh.home.domain.repository.city.CityRepository
import javax.inject.Inject

class DefaultCityUsecase @Inject constructor(private val cityRepository: CityRepository) :
    CityUsecase {
    override suspend fun invoke(q: String): Result<List<City>> = cityRepository.fetchCities(q = q)
}