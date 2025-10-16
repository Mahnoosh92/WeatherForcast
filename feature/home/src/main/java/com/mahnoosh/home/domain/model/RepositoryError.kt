package com.mahnoosh.home.domain.model

internal sealed class RepositoryError : Exception() {
    data class NetworkError(val code: Int, override val message: String) : RepositoryError()
    data class UnKnownError(override val message: String) : RepositoryError()
}