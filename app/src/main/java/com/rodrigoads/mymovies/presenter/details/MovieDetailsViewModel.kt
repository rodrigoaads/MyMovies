package com.rodrigoads.mymovies.presenter.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.domain.model.toMovieDetailsUiModel
import com.rodrigoads.mymovies.domain.usecases.GetMovieDetailsUseCase
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.details.model.MovieDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    val movieDetails = MutableLiveData<ResultUiState<MovieDetailsUiModel>>()

    fun getMovieDetails(id: Int){
        viewModelScope.launch {
            getMovieDetailsUseCase(id).collectLatest {
                when(it){
                    is ResultStatus.Loading -> {
                        movieDetails.postValue(ResultUiState.Loading)
                    }
                    is ResultStatus.Success -> {
                        movieDetails.postValue(ResultUiState.Success(it.data.toMovieDetailsUiModel()))
                    }
                    is ResultStatus.Error -> {
                        movieDetails.postValue(ResultUiState.Error(it.e))
                    }
                }
            }
        }
    }
}