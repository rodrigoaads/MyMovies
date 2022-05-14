package com.rodrigoads.mymovies.presenter.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rodrigoads.mymovies.domain.model.toSimilarMoviesUiModel
import com.rodrigoads.mymovies.domain.usecases.GetSimilarMoviesUseCase
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.details.model.SimilarMoviesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : ViewModel() {

    val similarMovies = MutableLiveData<PagingData<SimilarMoviesUiModel>>()

    fun getSimilarMovies(id: Int) {
        viewModelScope.launch {
            getSimilarMoviesUseCase(id, getSimilarMoviesPagingConfig()).cachedIn(viewModelScope)
                .collect {
                    similarMovies.postValue(it.map { similarMovies ->
                        similarMovies.toSimilarMoviesUiModel()
                    })
                }
        }
    }

    private fun getSimilarMoviesPagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE
    )

    companion object {
        const val PAGE_SIZE = 20
    }
}