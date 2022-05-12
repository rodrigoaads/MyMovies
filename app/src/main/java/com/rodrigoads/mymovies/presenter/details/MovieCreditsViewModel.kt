package com.rodrigoads.mymovies.presenter.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.domain.model.toMovieCreditsUiModel
import com.rodrigoads.mymovies.domain.usecases.GetMovieCreditsUseCase
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.details.model.MovieCreditsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCreditsViewModel @Inject constructor(
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase
): ViewModel() {

    val movieCredits = MutableLiveData<ResultUiState<MovieCreditsUiModel>>()

    fun getMovieCredits(id: Int){
        viewModelScope.launch {
            getMovieCreditsUseCase.getMovieCredits(id).collect {
                when(it){
                    is ResultStatus.Success -> {
                        movieCredits.postValue(ResultUiState.Success(it.data.toMovieCreditsUiModel()))
                    }
                    is ResultStatus.Error -> {
                        movieCredits.postValue(ResultUiState.Error(it.e))
                    }
                    is ResultStatus.Loading -> {
                        movieCredits.postValue(ResultUiState.Loading)
                    }
                }
            }
        }
    }
}