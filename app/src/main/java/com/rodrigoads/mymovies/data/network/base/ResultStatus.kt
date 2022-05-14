package com.rodrigoads.mymovies.data.network.base

sealed class ResultStatus<out T> {
    object Loading : ResultStatus<Nothing>()
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Error(val e: Exception) : ResultStatus<Nothing>()
}
