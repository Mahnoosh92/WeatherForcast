package com.mahnoosh.home.data.repository.city

import com.mahnoosh.home.data.datasource.remote.city.CityRemoteDatasource
import com.mahnoosh.home.domain.model.City
import com.mahnoosh.home.domain.model.RepositoryError
import com.mahnoosh.home.domain.repository.city.CityRepository
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import toDomain
import javax.inject.Inject

class DefaultCityRepository @Inject constructor(
    private val cityRemoteDatasource: CityRemoteDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CityRepository {
    override suspend fun fetchCities(q: String): Result<List<City>> = withContext(dispatcher) {
        try {
            val response = cityRemoteDatasource.fetchCities(q = q)
            if (response.isSuccessful)
                Result.success(response.body()?.map { it.toDomain() } ?: emptyList())
            else
                Result.failure(
                    RepositoryError.NetworkError(
                        code = response.code(),
                        message = response.errorBody().toString()
                    )
                )
        } catch (e: Exception) {
            Result.failure(RepositoryError.UnKnownError(message = e.message.toString()))
        }
    }
}