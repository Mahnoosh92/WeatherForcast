package com.mahnoosh.network.data.datasource.currennt

import com.mahnoosh.network.data.ApiService
import com.mahnoosh.network.data.model.CustomException
import com.mahnoosh.network.data.errorhandler.SafeApiCaller
import com.mahnoosh.network.data.model.CurrentWeatherDTO
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultCurrentWeatherRemoteDatasource @Inject constructor(
    private val safeApiCaller: SafeApiCaller,
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CurrentWeatherRemoteDatasource {
    override suspend fun getCurrentWeather(q: String): Result<CurrentWeatherDTO> =
        withContext(dispatcher) {
            val apiResult = safeApiCaller.safeApiCall {
                apiService.getCurrentWeather(q = q)
            }
            apiResult.fold(
                onSuccess = { response ->
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Result.success(responseBody)
                        } else {
                            Result.failure(CustomException.UnKnownException("API response body was empty"))
                        }
                    } else {
                        val code = response.code()
                        val message = response.message() ?: "HTTP error $code"
                        Result.failure(CustomException.ApiException(message, code))
                    }
                },
                onFailure = { throwable ->
                    val customException = throwable as? CustomException
                        ?: return@fold Result.failure(CustomException.UnKnownException("Unexpected exception type"))

                    Result.failure(customException)
                }
            )

        }
}