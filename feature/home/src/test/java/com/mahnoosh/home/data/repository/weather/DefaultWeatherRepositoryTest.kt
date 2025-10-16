package com.mahnoosh.home.data.repository.weather

import com.google.common.truth.Truth.assertThat
import com.mahnoosh.home.data.datasource.remote.weather.WeatherRemoteDatasource
import com.mahnoosh.home.domain.repository.weather.WeatherRepository
import com.mahnoosh.network.model.CurrentWeatherDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DefaultWeatherRepositoryTest {

    private val weatherRemoteDatasource = mockk<WeatherRemoteDatasource>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        weatherRepository = DefaultWeatherRepository(
            weatherRemoteDatasource = weatherRemoteDatasource,
            dispatcher = dispatcher
        )
    }

    @Test
    fun `fetchCurrentWeather returns exception when http network error happens`() =
        runTest(dispatcher.scheduler) {
            val errorResponse = Response.error<CurrentWeatherDTO>(
                400,
                "{\"key\":[\"somestuff\"]}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            coEvery { weatherRemoteDatasource.fetchCurrentWeather(q = any()) } returns errorResponse

            val result = weatherRepository.fetchCurrentWeather(q="")

            assertThat(result.isSuccess).isFalse()
            assertThat(result).isInstanceOf(Exception::class.java)
        }

    @Test
    fun `fetchCurrentWeather returns successful result when response succeeded`() =
        runTest(dispatcher.scheduler) {
            coEvery { weatherRemoteDatasource.fetchCurrentWeather(q = any()) } returns Response.success(
                CurrentWeatherDTO())

            val result = weatherRepository.fetchCurrentWeather(q="")

            assertThat(result.isSuccess).isTrue()
        }
}