package com.mahnoosh.home.data.datasource.remote.forcast

import com.mahnoosh.network.model.CurrentForcastDTO
import retrofit2.Response

interface ForcastRemoteDatasource {
    suspend fun fetchCurrentForcast(
        q: String,
        days: Int
    ): Response<CurrentForcastDTO>
}