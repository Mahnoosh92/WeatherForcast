package com.mahnoosh.home.domain.usecase.city

import com.mahnoosh.home.domain.model.City

interface CityUsecase {
    suspend operator fun invoke(q: String):Result<List<City>>
}