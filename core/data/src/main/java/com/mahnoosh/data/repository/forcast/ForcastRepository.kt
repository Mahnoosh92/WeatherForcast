package com.mahnoosh.data.repository.forcast

import com.mahnoosh.model.data.CurrentForcast

interface ForcastRepository {
    suspend fun fetchCurrentForcast(
        q: String,
        days: Int
    ): Result<CurrentForcast>
}