package com.mahnoosh.network.data.datasource.currennt

import com.google.common.truth.Truth.assertThat
import com.mahnoosh.network.data.ApiService
import com.mahnoosh.network.data.builder.CurrentWeatherDTOBuilder
import com.mahnoosh.network.data.errorhandler.SafeApiCaller
import com.mahnoosh.network.data.model.CurrentWeatherDTO
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


internal class DefaultCurrentWeatherRemoteDatasourceTest {
    private val safeApiCaller = mockk<SafeApiCaller>()
    private val apiService = mockk<ApiService>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var datasource: CurrentWeatherRemoteDatasource
    private val query = "London"

    @Before
    fun setUp() {
        datasource = DefaultCurrentWeatherRemoteDatasource(
            safeApiCaller = safeApiCaller,
            apiService = apiService,
            dispatcher = dispatcher
        )
    }

    @Test
    fun getCurrentWeather_successResponse_returnsSuccessResult() = runTest(dispatcher.scheduler) {
        val mockResponseValue = CurrentWeatherDTOBuilder().build()
        coEvery {
            safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentWeatherDTO>>())
        } returns Result.success(Response.success(mockResponseValue))

        val actual = datasource.getCurrentWeather(query)

        assertThat(actual.isSuccess).isTrue()
        assertThat(actual.getOrNull()).isEqualTo(mockResponseValue)
    }

    @Test
    fun getCurrentWeather_apiErrorResponse_returnsApiException() = runTest(dispatcher.scheduler) {
        val errorCode = 404
        val errorMessage = "Response.error()"
        val errorBody = "{}".toResponseBody("application/json".toMediaTypeOrNull())
        val errorResponse = Response.error<CurrentWeatherDTO>(errorCode, errorBody)
        coEvery {
            safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentWeatherDTO>>())
        } returns Result.success(errorResponse)

        val actual = datasource.getCurrentWeather(query)

        assertThat(actual.isFailure).isTrue()
        val exception = actual.exceptionOrNull()
        assertThat(exception).isInstanceOf(CustomException.ApiException::class.java)
        val apiException = exception as CustomException.ApiException
        assertThat(apiException.code).isEqualTo(errorCode)
        assertThat(apiException.message).isEqualTo(errorMessage)
    }

    @Test
    fun getCurrentWeather_successfulResponseWithNullBody_returnsUnKnownException() =
        runTest(dispatcher.scheduler) {
            val nullBodyResponse = Response.success<CurrentWeatherDTO>(null)
            coEvery {
                safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentWeatherDTO>>())
            } returns Result.success(nullBodyResponse)

            val actual = datasource.getCurrentWeather(query)

            assertThat(actual.isFailure).isTrue()
            val exception = actual.exceptionOrNull()
            assertThat(exception).isInstanceOf(CustomException.UnKnownException::class.java)
            val unKnownException = exception as CustomException.UnKnownException
            assertThat(unKnownException.message).contains("response body was empty")
        }

    @Test
    fun getCurrentWeather_knownFailure_returnsCorrectCustomException() =
        runTest(dispatcher.scheduler) {
            val networkException = CustomException.NetworkException("No internet connection")
            coEvery {
                safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentWeatherDTO>>())
            } returns Result.failure(networkException)

            val actual = datasource.getCurrentWeather(query)

            assertThat(actual.isFailure).isTrue()
            assertThat(actual.exceptionOrNull()).isSameInstanceAs(networkException)
        }

    @Test
    fun getCurrentWeather_unexpectedFailure_returnsUnKnownException() =
        runTest(dispatcher.scheduler) {
            val unexpectedException = IllegalStateException("Something broke internally")
            coEvery {
                safeApiCaller.safeApiCall(any<suspend () -> Response<CurrentWeatherDTO>>())
            } returns Result.failure(unexpectedException)

            val actual = datasource.getCurrentWeather(query)

            assertThat(actual.isFailure).isTrue()
            val exception = actual.exceptionOrNull()
            assertThat(exception).isInstanceOf(CustomException.UnKnownException::class.java)
            val unKnownException = exception as CustomException.UnKnownException
            assertThat(unKnownException.message).contains("Unexpected exception type")
        }
}