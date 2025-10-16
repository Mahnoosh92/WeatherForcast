package com.mahnoosh.home.data.repository.forcast

import com.mahnoosh.home.data.datasource.remote.forcast.ForcastRemoteDatasource
import com.mahnoosh.home.domain.model.CurrentForcast
import com.mahnoosh.home.domain.model.RepositoryError
import com.mahnoosh.home.domain.repository.forcast.ForcastRepository
import com.mahnoosh.network.model.CurrentForcastDTO
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import toDomain
import javax.inject.Inject

class DefaultForcastRepository @Inject constructor(
    private val forcastRemoteDatasource: ForcastRemoteDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) :
    ForcastRepository {
    override suspend fun fetchCurrentForcast(
        q: String,
        days: Int
    ): Result<CurrentForcast> = withContext(dispatcher) {
        try {
            val response = forcastRemoteDatasource.fetchCurrentForcast(q = q, days = days)
            if (response.isSuccessful)
                Result.success(response.body()?.toDomain() ?: CurrentForcastDTO().toDomain())
            else
                Result.failure(Exception(response.errorBody().toString()))

        } catch (e: Exception) {
            Result.failure(Exception(e.message.toString()))
        }
    }
}