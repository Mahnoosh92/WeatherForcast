package com.mahnoosh.data.datasource.remote.forcast

import com.mahnoosh.model.data.CurrentForcast

interface ForcastRemoteDatasource {
    suspend fun getCurrentForcast(
        q: String,
        days: Int
    ): Result<CurrentForcast>
}