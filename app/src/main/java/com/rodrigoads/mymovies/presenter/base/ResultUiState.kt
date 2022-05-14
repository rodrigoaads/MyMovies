package com.rodrigoads.mymovies.presenter.base

sealed class ResultUiState<out T> {
    object Loading : ResultUiState<Nothing>()
    data class Success<out T>(val data: T) : ResultUiState<T>()
    data class Error(val e: Exception) : ResultUiState<Nothing>()
}
