package com.mahnoosh.home.data.repository.city

import com.google.common.truth.Truth.assertThat
import com.mahnoosh.home.data.datasource.remote.city.CityRemoteDatasource
import com.mahnoosh.home.domain.model.RepositoryError
import com.mahnoosh.home.domain.repository.city.CityRepository
import com.mahnoosh.network.model.CityDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class DefaultCityRepositoryTest {

    private val cityRemoteDatasource = mockk<CityRemoteDatasource>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var cityRepository: CityRepository

    @Before
    fun setUp() {
        cityRepository = DefaultCityRepository(
            cityRemoteDatasource = cityRemoteDatasource,
            dispatcher = dispatcher
        )
    }

    @Test
    fun `fetchCities returns NetworkError when response failed`() = runTest(dispatcher.scheduler) {
        val errorResponse = Response.error<List<CityDTO>>(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        coEvery { cityRemoteDatasource.fetchCities(q = any()) } returns errorResponse

        val result = cityRepository.fetchCities(q = "")

        assertThat(result.isSuccess).isFalse()
        assertThat(result.exceptionOrNull()).isInstanceOf(RepositoryError.NetworkError::class.java)
    }

    @Test
    fun `fetchCities returns UnKnownError when exception is thrown`() =
        runTest(dispatcher.scheduler) {
            coEvery { cityRemoteDatasource.fetchCities(q = any()) } throws Exception()

            val result = cityRepository.fetchCities(q = "")

            assertThat(result.isSuccess).isFalse()
            assertThat(result.exceptionOrNull()).isInstanceOf(RepositoryError.UnKnownError::class.java)
        }

    @Test
    fun `fetchCities returns successful result when request is succeeded`() =
        runTest(dispatcher.scheduler) {
            coEvery { cityRemoteDatasource.fetchCities(q = any()) } returns Response.success(
                emptyList()
            )

            val result = cityRepository.fetchCities(q = "")

            assertThat(result.isSuccess).isTrue()
            assertThat(result.getOrNull()?.size).isEqualTo(0)
        }
}