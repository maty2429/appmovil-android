package com.example.soporte.core.network

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String? = null, val throwable: Throwable? = null) : NetworkResult<Nothing>()
    data class Loading(val isLoading: Boolean) : NetworkResult<Nothing>()
}
