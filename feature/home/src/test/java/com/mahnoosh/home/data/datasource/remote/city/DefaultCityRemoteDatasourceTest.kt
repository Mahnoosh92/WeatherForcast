package com.mahnoosh.home.data.datasource.remote.city

import com.mahnoosh.network.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.Response
import org.junit.Before
import org.junit.Test

class DefaultCityRemoteDatasourceTest {

    private val apiService = mockk<ApiService>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var cityRemoteDatasource: CityRemoteDatasource

    @Before
    fun setUp() {
        cityRemoteDatasource =
            DefaultCityRemoteDatasource(apiService = apiService, dispatcher = dispatcher)
    }

    @Test
    fun `verify fetchCities is called on apiService when fetchCities is called`() =
        runTest(dispatcher.scheduler) {
            coEvery { apiService.fetchCities(q = any()) } returns retrofit2.Response.success(
                emptyList()
            )

            cityRemoteDatasource.fetchCities(q = "")

            coVerify(exactly = 1) { apiService.fetchCities(q = any()) }

        }
}