package com.mahnoosh.network.data.datasource.city

import com.google.common.truth.Truth.assertThat
import com.mahnoosh.network.data.ApiService
import com.mahnoosh.network.data.builder.CityDTOBuilder
import com.mahnoosh.network.data.errorhandler.SafeApiCaller
import com.mahnoosh.network.data.model.CityDTO
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


internal class DefaultCitiesRemoteDatasourceTest {
    private val safeApiCaller = mockk<SafeApiCaller>()
    private val apiService = mockk<ApiService>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var datasource: CitiesRemoteDatasource
    private val query = "London"

    @Before
    fun setUp() {
        datasource = DefaultCitiesRemoteDatasource(
            safeApiCaller = safeApiCaller,
            apiService = apiService,
            dispatcher = dispatcher
        )
    }

    @Test
    fun getCities_successResponse_returnsSuccessResult() = runTest(dispatcher.scheduler) {
        val mockCityList = listOf(
            CityDTOBuilder().withId(1).withCountry("London").build(),
            CityDTOBuilder().withId(2).withCountry("Paris").build()
        )
        coEvery {
            safeApiCaller.safeApiCall(any<suspend () -> Response<List<CityDTO>>>())
        } returns Result.success(Response.success(mockCityList))

        val actual = datasource.getCities(query)

        assertThat(actual.isSuccess).isTrue()
        assertThat(actual.getOrNull()).isEqualTo(mockCityList)
    }

    @Test
    fun getCities_apiErrorResponse_returnsApiException() = runTest(dispatcher.scheduler) {
        val errorCode = 404
        val errorMessage = "Response.error()"
        val errorBody = "{}".toResponseBody("application/json".toMediaTypeOrNull())
        val errorResponse = Response.error<List<CityDTO>>(errorCode, errorBody)
        coEvery {
            safeApiCaller.safeApiCall(any<suspend () -> Response<List<CityDTO>>>())
        } returns Result.success(errorResponse)

        val actual = datasource.getCities(query)

        assertThat(actual.isFailure).isTrue()
        val exception = actual.exceptionOrNull()
        assertThat(exception).isInstanceOf(CustomException.ApiException::class.java)
        val apiException = exception as CustomException.ApiException
        assertThat(apiException.code).isEqualTo(errorCode)
        assertThat(apiException.message).isEqualTo(errorMessage)
    }

    @Test
    fun getCities_successfulResponseWithNullBody_returnsUnKnownException() =
        runTest(dispatcher.scheduler) {
            val nullBodyResponse = Response.success<List<CityDTO>>(null)
            coEvery {
                safeApiCaller.safeApiCall(any<suspend () -> Response<List<CityDTO>>>())
            } returns Result.success(nullBodyResponse)

            val actual = datasource.getCities(query)

            assertThat(actual.isFailure).isTrue()
            val exception = actual.exceptionOrNull()
            assertThat(exception).isInstanceOf(CustomException.UnKnownException::class.java)
            val unKnownException = exception as CustomException.UnKnownException
            assertThat(unKnownException.message).contains("response body was empty")
        }

    @Test
    fun getCities_knownFailure_returnsCorrectCustomException() = runTest(dispatcher.scheduler) {
        val networkException = CustomException.NetworkException("No internet connection")
        coEvery {
            safeApiCaller.safeApiCall(any<suspend () -> Response<List<CityDTO>>>())
        } returns Result.failure(networkException)

        val actual = datasource.getCities(query)

        assertThat(actual.isFailure).isTrue()
        assertThat(actual.exceptionOrNull()).isSameInstanceAs(networkException)
    }

    @Test
    fun getCities_unexpectedFailure_returnsUnKnownException() = runTest(dispatcher.scheduler) {
        val unexpectedException = IllegalStateException("Something broke internally")
        coEvery {
            safeApiCaller.safeApiCall(any<suspend () -> Response<List<CityDTO>>>())
        } returns Result.failure(unexpectedException)

        val actual = datasource.getCities(query)

        assertThat(actual.isFailure).isTrue()
        val exception = actual.exceptionOrNull()
        assertThat(exception).isInstanceOf(CustomException.UnKnownException::class.java)
        val unKnownException = exception as CustomException.UnKnownException
        assertThat(unKnownException.message).contains("Unexpected exception type")
    }
}