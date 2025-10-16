package com.mahnoosh.home.domain.usecase.forcast

import com.mahnoosh.home.domain.model.CurrentForcast
import com.mahnoosh.home.domain.repository.forcast.ForcastRepository
import javax.inject.Inject

class DefaultForcastUsecase @Inject constructor(private val forcastRepository: ForcastRepository) :
    ForcastUsecase {
    override suspend fun invoke(
        q: String,
        days: Int
    ): Result<CurrentForcast> = forcastRepository.fetchCurrentForcast(q = q, days = days)
}