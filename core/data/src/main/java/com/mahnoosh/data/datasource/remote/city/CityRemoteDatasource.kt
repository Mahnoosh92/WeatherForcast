package com.mahnoosh.data.datasource.remote.city

import com.mahnoosh.model.data.City

interface CityRemoteDatasource {
    suspend fun getCities(
        q: String,
    ): Result<List<City>>
}