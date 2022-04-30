package com.rodrigoads.mymovies.presenter.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rodrigoads.mymovies.domain.model.MoviesBySearch
import com.rodrigoads.mymovies.domain.model.toMoviesBySearchUiModel
import com.rodrigoads.mymovies.domain.usecases.GetMoviesBySearchUseCase
import com.rodrigoads.mymovies.presenter.search.model.SearchMovieUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val getMoviesBySearchUseCase: GetMoviesBySearchUseCase
) : ViewModel() {

    val searchMovie = MutableLiveData<PagingData<SearchMovieUiModel>>()

    fun getMoviesBySearch(query: String) {
        viewModelScope.launch {
            getMoviesBySearchUseCase(query, getSearchMoviePagingConfig())
                .cachedIn(viewModelScope).collect {
                    searchMovie.postValue(it.map { moviesBySearch ->
                        moviesBySearch.toMoviesBySearchUiModel()
                    })
                }
        }
    }

    private fun getSearchMoviePagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = PAGE_SIZE
        )
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}