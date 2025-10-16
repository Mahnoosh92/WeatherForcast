package com.mahnoosh.home.data.datasource.remote.weather

import com.mahnoosh.network.ApiService
import com.mahnoosh.network.model.CurrentWeatherDTO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DefaultWeatherRemoteDatasourceTest {

    private val apiService = mockk<ApiService>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var weatherRemoteDatasource: WeatherRemoteDatasource

    @Before
    fun setUp() {
        weatherRemoteDatasource =
            DefaultWeatherRemoteDatasource(apiService = apiService, dispatcher = dispatcher)
    }

    @Test
    fun `verify fetchCurrentWeather is called on apiService when fetchCurrentWeather is called`() =
        runTest(dispatcher.scheduler) {
            coEvery { apiService.fetchCurrentWeather(q = any()) } returns Response.success(
                CurrentWeatherDTO()
            )

            weatherRemoteDatasource.fetchCurrentWeather(q = "")

            coVerify(exactly = 1) { apiService.fetchCurrentWeather(q = any()) }
        }
}