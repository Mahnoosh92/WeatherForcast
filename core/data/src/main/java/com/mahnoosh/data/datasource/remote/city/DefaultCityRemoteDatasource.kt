package com.mahnoosh.data.datasource.remote.city

import com.mahnoosh.model.data.City
import com.mahnoosh.model.data.DomainError
import com.mahnoosh.network.data.ApiService
import com.mahnoosh.network.data.model.CustomException
import com.mahnoosh.network.data.errorhandler.SafeApiCaller
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import toDomain
import toDomainError
import javax.inject.Inject

class DefaultCityRemoteDatasource @Inject constructor(
    private val apiService: ApiService,
    private val safeApiCaller: SafeApiCaller,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CityRemoteDatasource {
    override suspend fun getCities(q: String): Result<List<City>> = withContext(dispatcher) {
        val apiResult = safeApiCaller.safeApiCall {
            apiService.getCities(q = q)
        }

        apiResult.fold(
            onSuccess = { response ->
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        try {
                            Result.success(responseBody.map { it.toDomain() })
                        } catch (e: DomainError.RequiredFieldMissingError) {
                            Result.failure(e)
                        } catch (e: Exception) {
                            Result.failure(DomainError.UnknownError(e.message ?: "Mapping error"))
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
                val customException = throwable as? CustomException
                    ?: return@fold Result.failure(DomainError.UnknownError("Unexpected exception type"))

                Result.failure(customException.toDomainError())
            }
        )
        Result.success(emptyList())
    }
}