package com.mahnoosh.data.repository.forcast

import com.mahnoosh.data.datasource.remote.forcast.ForcastRemoteDatasource
import com.mahnoosh.model.data.CurrentForcast
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DefaultForcastRepository @Inject constructor(
    private val forcastRemoteDatasource: ForcastRemoteDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) :
    ForcastRepository {
    override suspend fun fetchCurrentForcast(
        q: String,
        days: Int
    ): Result<CurrentForcast> = forcastRemoteDatasource.getCurrentForcast(q = q, days = days)
}