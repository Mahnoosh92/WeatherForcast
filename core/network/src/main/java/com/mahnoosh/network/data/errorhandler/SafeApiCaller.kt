package com.mahnoosh.network.data.errorhandler

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SafeApiCaller @Inject constructor(
    private val errorParser: HttpErrorParser
) {
    /**
     * Executes an API call safely, handling exceptions and parsing error bodies.
     * @param apiCall The suspending lambda containing the Retrofit API method.
     * @return Result<T> where T is the success type of the API call.
     */
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Result.success(apiCall.invoke())
        } catch (e: Exception) {
            Result.failure(mapToCustomException(e))
        }
    }

    private fun mapToCustomException(e: Exception): CustomException {
        return when (e) {
            is HttpException -> {
                val code = e.code()
                val rawErrorBody = e.response()?.errorBody()?.string()

                val genericError = rawErrorBody?.let { errorParser.parseGenericError(it) }
                val message = genericError?.error?.message ?: "API Error $code"
                CustomException.ApiException(message, code)
            }

            is IOException -> CustomException.NetworkException() // Network connectivity issues
            else -> CustomException.UnKnownException(e.message)
        }
    }
}