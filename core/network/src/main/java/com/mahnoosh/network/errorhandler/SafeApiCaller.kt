package com.mahnoosh.network.errorhandler

import retrofit2.HttpException
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

                when (code) {
                    401, 403 -> {
                        val tokenError =
                            rawErrorBody?.let { errorParser.parseGenericError(it) }
                        CustomException.InvalidTokenException(
                            tokenError?.error?.message ?: "Session expired or invalid token"
                        )
                    }

                    in 400..599 -> {
                        val genericError = rawErrorBody?.let { errorParser.parseGenericError(it) }
                        val message = genericError?.error?.message ?: "API Error $code"
                        CustomException.ApiException(message, code)
                    }

                    else -> CustomException.ApiException("Unknown HTTP error: $code", code)
                }
            }

            is java.io.IOException -> CustomException.NetworkException() // Network connectivity issues
            else -> CustomException.UnKnownException(e.message)
        }
    }
}