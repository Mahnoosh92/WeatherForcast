package com.mahnoosh.home.domain.repository.forcast

import com.mahnoosh.home.domain.model.CurrentForcast

interface ForcastRepository {
    suspend fun fetchCurrentForcast(
        q: String,
        days: Int
    ): Result<CurrentForcast>
}