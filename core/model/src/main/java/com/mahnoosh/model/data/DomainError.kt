package com.mahnoosh.model.data

sealed class DomainError(message: String) : Exception(message) {
    data class RequiredFieldMissingError(val fieldName: String) :
        DomainError(fieldName)

    data class NetworkError(override val message: String) : DomainError(message)
    data class ApiError(override val message: String, val code: Int) : DomainError(message)
    data class InvalidTokenError(override val message: String) : DomainError(message)
    data class UnknownError(override val message: String) : DomainError(message)
}