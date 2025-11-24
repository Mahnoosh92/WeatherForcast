package com.mahnoosh.network.data.datasource.forcast

import com.google.common.truth.Truth.assertThat
import com.mahnoosh.network.data.ApiService
import com.mahnoosh.network.data.builder.CurrentForcastDTOBuilder
import com.mahnoosh.network.data.errorhandler.SafeApiCaller
import com.mahnoosh.network.data.model.CurrentForcastDTO
import com.mahnoosh.network.data.model.CustomException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

internal class DefaultCurrentForcastRemoteDatasourceTest {
    private val safeApiCaller = mockk<SafeApiCaller>()
    private val apiService = mockk<ApiService>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var datasource: CurrentForcastRemoteDatasource
    private val query = "London"
    private val days = 7

    @Before
    fun setUp() {
        datasource = DefaultCurrentForcastRemoteDatasource(
            safeApiCaller = safeApiCaller,
            apiService = apiService,
            dispatcher = dispatcher
        )
    }

    @Test
    fun getCurrentForcast_successResponse_returnsSuccessResult() = runTest(dispatcher.scheduler) {
        val mockResponseValue = CurrentForcastDTOBuilder().build()
        coEvery {
            safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentForcastDTO>>())
        } returns Result.success(Response.success(mockResponseValue))

        val actual = datasource.getCurrentForcast(query, days = days)

        assertThat(actual.isSuccess).isTrue()
        assertThat(actual.getOrNull()).isEqualTo(mockResponseValue)
    }

    @Test
    fun getCurrentForcast_apiErrorResponse_returnsApiException() = runTest(dispatcher.scheduler) {
        val errorCode = 404
        val errorMessage = "Response.error()"
        val errorBody = "{}".toResponseBody("application/json".toMediaTypeOrNull())
        val errorResponse = Response.error<CurrentForcastDTO>(errorCode, errorBody)
        coEvery {
            safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentForcastDTO>>())
        } returns Result.success(errorResponse)

        val actual = datasource.getCurrentForcast(query, days = days)

        assertThat(actual.isFailure).isTrue()
        val exception = actual.exceptionOrNull()
        assertThat(exception).isInstanceOf(CustomException.ApiException::class.java)
        val apiException = exception as CustomException.ApiException
        assertThat(apiException.code).isEqualTo(errorCode)
        assertThat(apiException.message).isEqualTo(errorMessage)
    }

    @Test
    fun getCurrentForcast_successfulResponseWithNullBody_returnsUnKnownException() =
        runTest(dispatcher.scheduler) {
            val nullBodyResponse = Response.success<CurrentForcastDTO>(null)
            coEvery {
                safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentForcastDTO>>())
            } returns Result.success(nullBodyResponse)

            val actual = datasource.getCurrentForcast(query, days = days)

            assertThat(actual.isFailure).isTrue()
            val exception = actual.exceptionOrNull()
            assertThat(exception).isInstanceOf(CustomException.UnKnownException::class.java)
            val unKnownException = exception as CustomException.UnKnownException
            assertThat(unKnownException.message).contains("response body was empty")
        }

    @Test
    fun getCurrentForcast_knownFailure_returnsCorrectCustomException() =
        runTest(dispatcher.scheduler) {
            val networkException = CustomException.NetworkException("No internet connection")
            coEvery {
                safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentForcastDTO>>())
            } returns Result.failure(networkException)

            val actual = datasource.getCurrentForcast(query, days = days)

            assertThat(actual.isFailure).isTrue()
            assertThat(actual.exceptionOrNull()).isSameInstanceAs(networkException)
        }

    @Test
    fun getCurrentForcast_unexpectedFailure_returnsUnKnownException() =
        runTest(dispatcher.scheduler) {
            val unexpectedException = IllegalStateException("Something broke internally")
            coEvery {
                safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentForcastDTO>>())
            } returns Result.failure(unexpectedException)

            val actual = datasource.getCurrentForcast(query, days = days)

            assertThat(actual.isFailure).isTrue()
            val exception = actual.exceptionOrNull()
            assertThat(exception).isInstanceOf(CustomException.UnKnownException::class.java)
            val unKnownException = exception as CustomException.UnKnownException
            assertThat(unKnownException.message).contains("Unexpected exception type")
        }
}