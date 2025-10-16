package com.mahnoosh.home.data.datasource.remote.forcast

import com.mahnoosh.network.ApiService
import com.mahnoosh.network.model.CurrentForcastDTO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class DefaultForcastRemoteDatasourceTest {

    private val apiService = mockk<ApiService>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var forcastRemoteDatasource: ForcastRemoteDatasource

    @Before
    fun setUp() {
        forcastRemoteDatasource =
            DefaultForcastRemoteDatasource(apiService = apiService, dispatcher = dispatcher)
    }

    @Test
    fun `verify fetchCurrentForcast is called on apiService when fetchCurrentForcast is called`() =
        runTest(dispatcher.scheduler) {
            coEvery {
                apiService.fetchCurrentForcast(
                    q = any(),
                    days = any()
                )
            } returns Response.success(
                CurrentForcastDTO()
            )

            forcastRemoteDatasource.fetchCurrentForcast(q = "", days = 8)

            coVerify(exactly = 1) {
                apiService.fetchCurrentForcast(
                    q = any(),
                    days = any()
                )
            }
        }
}