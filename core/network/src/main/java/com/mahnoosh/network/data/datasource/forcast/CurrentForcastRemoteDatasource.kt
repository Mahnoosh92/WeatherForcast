package com.mahnoosh.network.data.datasource.forcast

import com.mahnoosh.network.data.model.CurrentForcastDTO

interface CurrentForcastRemoteDatasource {
    suspend fun getCurrentForcast(q: String, days: Int): Result<CurrentForcastDTO>
}