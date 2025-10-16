package com.mahnoosh.home.domain.usecase.forcast

import com.mahnoosh.home.domain.model.CurrentForcast

interface ForcastUsecase {
    suspend operator fun invoke(
        q: String,
        days: Int
    ):Result<CurrentForcast>
}