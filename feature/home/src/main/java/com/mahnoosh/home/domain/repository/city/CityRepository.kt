package com.mahnoosh.home.domain.repository.city

import com.mahnoosh.home.domain.model.City

interface CityRepository {
    suspend fun fetchCities(
        q: String,
    ): Result<List<City>>
}