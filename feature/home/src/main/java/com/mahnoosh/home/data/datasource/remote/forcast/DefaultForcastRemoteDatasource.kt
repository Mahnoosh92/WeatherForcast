package com.mahnoosh.home.data.datasource.remote.forcast

import com.mahnoosh.network.ApiService
import com.mahnoosh.network.model.CurrentForcastDTO
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DefaultForcastRemoteDatasource @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ForcastRemoteDatasource {
    override suspend fun fetchCurrentForcast(
        q: String,
        days: Int
    ): Response<CurrentForcastDTO> = withContext(dispatcher) {
        apiService.fetchCurrentForcast(q = q, days = days)
    }
}