package com.rodrigoads.mymovies.presenter.bycategory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rodrigoads.mymovies.domain.model.toMoviesByCategoryUiModel
import com.rodrigoads.mymovies.domain.usecases.GetMoviesByCategoryUseCase
import com.rodrigoads.mymovies.presenter.bycategory.model.MoviesByCategoryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesByCategoryViewModel @Inject constructor(
    private val getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase
) : ViewModel() {

    val moviesByCategory = MutableLiveData<PagingData<MoviesByCategoryUiModel>>()

    fun getMoviesByCategory(genre: Int){
        viewModelScope.launch {
            getMoviesByCategoryUseCase(genre, getMoviesByCategoryPagingConfig()).cachedIn(viewModelScope).collect {
                moviesByCategory.postValue(it.map { moviesByCategory ->
                    moviesByCategory.toMoviesByCategoryUiModel()
                })
            }
        }
    }

    private fun getMoviesByCategoryPagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE
    )

    companion object {
        const val PAGE_SIZE = 20
    }
}