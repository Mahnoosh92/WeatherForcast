package com.mahnoosh.network.data.datasource.city

import com.mahnoosh.network.data.model.CityDTO

interface CitiesRemoteDatasource {
    suspend fun getCities(
        q: String,
    ): Result<List<CityDTO>>
}