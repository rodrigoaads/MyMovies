package com.rodrigoads.mymovies.presenter.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.domain.model.toCategoriesUiModel
import com.rodrigoads.mymovies.domain.usecases.GetMovieCategoriesUseCase
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.categories.model.CategoriesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getMovieCategoriesUseCase: GetMovieCategoriesUseCase
) : ViewModel() {
    val categories = MutableLiveData<ResultUiState<List<CategoriesUiModel>>>()

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            getMovieCategoriesUseCase.getMovieCategories().collectLatest {
                 when(it){
                    is ResultStatus.Loading -> {
                        categories.postValue(ResultUiState.Loading)
                    }
                    is ResultStatus.Success -> {
                        categories.postValue(ResultUiState.Success(it.data.genres.map { movieGenres ->
                            movieGenres.toCategoriesUiModel()
                        }))
                    }
                    is ResultStatus.Error -> {
                        categories.postValue(ResultUiState.Error(it.e))
                    }
                }
            }
        }
    }
}