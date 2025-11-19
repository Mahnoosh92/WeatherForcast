package com.mahnoosh.data.datasource.remote.weather

import com.mahnoosh.model.data.CurrentWeather
import com.mahnoosh.model.data.DomainError
import com.mahnoosh.network.data.ApiService
import com.mahnoosh.network.data.errorhandler.CustomException
import com.mahnoosh.network.data.errorhandler.SafeApiCaller
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import toDomain
import toDomainError
import javax.inject.Inject

class DefaultWeatherRemoteDatasource @Inject constructor(
    private val apiService: ApiService,
    private val safeApiCaller: SafeApiCaller,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : WeatherRemoteDatasource {
    override suspend fun fetchCurrentWeather(q: String): Result<CurrentWeather> =
        withContext(dispatcher) {
            val apiResult = safeApiCaller.safeApiCall {
                apiService.getCurrentWeather(q = q)
            }

            apiResult.fold(
                onSuccess = { response ->
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            try {
                                Result.success(responseBody.toDomain())
                            } catch (e: DomainError.RequiredFieldMissingError) {
                                Result.failure(e)
                            } catch (e: Exception) {
                                Result.failure(
                                    DomainError.UnknownError(
                                        e.message ?: "Mapping error"
                                    )
                                )
                            }
                        } else {
                            Result.failure(DomainError.UnknownError("API response body was empty"))
                        }
                    } else {
                        val code = response.code()
                        val message = response.message() ?: "HTTP error $code"
                        Result.failure(DomainError.ApiError(message, code))
                    }
                },
                onFailure = { throwable ->
                    val error = throwable as? CustomException
                        ?: return@fold Result.failure(DomainError.UnknownError("Unexpected exception type"))
                    Result.failure(error.toDomainError())
                }
            )
        }
}