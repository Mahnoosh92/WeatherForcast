package com.mahnoosh.home.data.datasource.remote.city

import com.mahnoosh.network.ApiService
import com.mahnoosh.network.model.CityDTO
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DefaultCityRemoteDatasource @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CityRemoteDatasource {
    override suspend fun fetchCities(q: String): Response<List<CityDTO>> = withContext(dispatcher) {
        apiService.fetchCities(q = q)
    }
}