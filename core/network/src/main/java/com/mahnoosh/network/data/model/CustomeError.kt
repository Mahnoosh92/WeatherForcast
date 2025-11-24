package com.mahnoosh.network.data.model

sealed class CustomException(message: String?) : Exception(message) {

    data class NetworkException(override val message: String? = "Network error occurred") :
        CustomException(message)

    data class ApiException(override val message: String?, val code: Int) : CustomException(message)

    data class UnKnownException(override val message: String? = "UnKnown Error Occurred") :
        CustomException(message)
}