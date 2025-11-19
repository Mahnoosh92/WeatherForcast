package com.mahnoosh.data.repository.city

import com.mahnoosh.model.data.City

interface CityRepository {
    suspend fun fetchCities(
        q: String,
    ): Result<List<City>>
}