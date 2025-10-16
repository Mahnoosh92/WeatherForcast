package com.mahnoosh.home.data.repository.forcast

import com.google.common.truth.Truth.assertThat
import com.mahnoosh.home.data.datasource.remote.forcast.ForcastRemoteDatasource
import com.mahnoosh.home.domain.repository.forcast.ForcastRepository
import com.mahnoosh.network.model.CurrentForcastDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DefaultForcastRepositoryTest {

    private val focastRemoteDatasource = mockk<ForcastRemoteDatasource>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var forcastRepository: ForcastRepository

    @Before
    fun setUp() {
        forcastRepository = DefaultForcastRepository(
            forcastRemoteDatasource = focastRemoteDatasource,
            dispatcher = dispatcher
        )
    }

    @Test
    fun `fetchCurrentForcast returns exception when http network error`() =
        runTest(dispatcher.scheduler) {
            val errorResponse = Response.error<CurrentForcastDTO>(
                400,
                "{\"key\":[\"somestuff\"]}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            coEvery {
                focastRemoteDatasource.fetchCurrentForcast(
                    q = any(),
                    days = any()
                )
            } returns errorResponse

            val result = forcastRepository.fetchCurrentForcast(q = "", days = 7)

            assertThat(result.isSuccess).isFalse()
            assertThat(result.exceptionOrNull()).isInstanceOf(Exception::class.java)
        }

    @Test
    fun `fetchCurrentForcast returns exception when forcastRemoteDatasource throws unexpected error`() =
        runTest(dispatcher.scheduler) {
            coEvery {
                focastRemoteDatasource.fetchCurrentForcast(
                    q = any(),
                    days = any()
                )
            } throws Exception()

            val result = forcastRepository.fetchCurrentForcast(q = "", days = 7)

            assertThat(result.isSuccess).isFalse()
            assertThat(result.exceptionOrNull()).isInstanceOf(Exception::class.java)
        }

    @Test
    fun `fetchCurrentForcast returns successful result when response succeeded`() =
        runTest(dispatcher.scheduler) {
            coEvery {
                focastRemoteDatasource.fetchCurrentForcast(
                    q = any(),
                    days = any()
                )
            } returns Response.success(CurrentForcastDTO())

            val result = forcastRepository.fetchCurrentForcast(q = "", days = 7)

            assertThat(result.isSuccess).isTrue()
        }
}