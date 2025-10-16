package com.mahnoosh.home.data.datasource.remote.city

import com.mahnoosh.network.model.CityDTO
import retrofit2.Response

interface CityRemoteDatasource {
    suspend fun fetchCities(
        q: String,
    ): Response<List<CityDTO>>
}