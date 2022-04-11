package com.rodrigoads.mymovies.presenter.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.domain.model.toPopularMoviesUiModel
import com.rodrigoads.mymovies.domain.usecases.GetPopularMoviesUseCase
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.popular.model.PopularMoviesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): ViewModel() {
    val firstPopularMovie = MutableLiveData<ResultUiState<PopularMoviesUiModel?>>()

    fun getPopularMovies() : Flow<PagingData<PopularMoviesUiModel>> {
        return getPopularMoviesUseCase(getPopularMoviesPagingConfig()){
            viewModelScope.launch {
                it.asFlow().collectLatest{
                    when(it){
                        is ResultStatus.Loading -> {
                            firstPopularMovie.postValue(ResultUiState.Loading)
                        }
                        is ResultStatus.Success -> {
                            firstPopularMovie.postValue(ResultUiState.Success(it.data?.toPopularMoviesUiModel()))
                        }
                        is ResultStatus.Error -> {
                            firstPopularMovie.postValue(ResultUiState.Error(it.e))
                        }
                    }
                }
            }
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