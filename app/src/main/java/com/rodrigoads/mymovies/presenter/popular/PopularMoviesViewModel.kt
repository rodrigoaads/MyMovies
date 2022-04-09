package com.rodrigoads.mymovies.presenter.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rodrigoads.mymovies.domain.model.toPopularMoviesUiModel
import com.rodrigoads.mymovies.domain.usecases.GetPopularMoviesUseCase
import com.rodrigoads.mymovies.presenter.popular.model.PopularMoviesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): ViewModel() {
    val firstPopularMovie = MutableLiveData<PopularMoviesUiModel>()

    fun getPopularMovies() : Flow<PagingData<PopularMoviesUiModel>> {
        return getPopularMoviesUseCase(getPopularMoviesPagingConfig()){
            firstPopularMovie.postValue(it?.toPopularMoviesUiModel())
        }.map {
            it.map { popularMovies ->
                popularMovies.toPopularMoviesUiModel()
            }
        }.cachedIn(viewModelScope)
    }

    private fun getPopularMoviesPagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE
    )

    companion object {
        const val PAGE_SIZE = 20
    }
}