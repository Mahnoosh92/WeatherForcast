package com.mahnoosh.home.domain.usecase.city

import com.mahnoosh.home.domain.repository.city.CityRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class DefaultCityUsecaseTest {

    private val cityRepository = mockk<CityRepository>()

    private lateinit var cityUsecase: CityUsecase

    @Before
    fun setUp() {
        cityUsecase = DefaultCityUsecase(cityRepository = cityRepository)
    }

    @Test
    fun `verify fetchCities is called on cityRepository when invoke is called`() = runTest {
        coEvery { cityRepository.fetchCities(q = any()) } returns Result.success(emptyList())

        cityUsecase(q = "")

        coVerify(exactly = 1) { cityRepository.fetchCities(q = any()) }
    }
}