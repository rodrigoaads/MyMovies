package com.rodrigoads.mymovies.presenter.details

import androidx.lifecycle.*
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.domain.model.MovieDetails
import com.rodrigoads.mymovies.domain.model.toMovieDetailsUiModel
import com.rodrigoads.mymovies.domain.model.toWatchLaterUiModel
import com.rodrigoads.mymovies.domain.usecases.GetMovieDetailsUseCase
import com.rodrigoads.mymovies.domain.usecases.GetWatchLaterUseCase
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.details.model.MovieDetailsUiModel
import com.rodrigoads.mymovies.presenter.watchlater.WatchLaterViewModel
import com.rodrigoads.mymovies.presenter.watchlater.model.WatchLaterUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    getWatchLaterUseCase: GetWatchLaterUseCase
) : ViewModel() {

    val movieDetails = MutableLiveData<ResultUiState<MovieDetailsUiModel>>()
    private val watchLaterList = MutableLiveData<List<WatchLaterUiModel>>()

    init {
        viewModelScope.launch {
            getWatchLaterUseCase.getAllWatchLaterMovies().collect {
                watchLaterList.postValue(it.map { watchLaterMovie ->
                    watchLaterMovie.toWatchLaterUiModel()
                })
            }
        }
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(id).collectLatest {
                when (it) {
                    is ResultStatus.Loading -> {
                        movieDetails.postValue(ResultUiState.Loading)
                    }
                    is ResultStatus.Success -> {
                        watchLaterList.value?.forEach { watchLaterMovie ->
                            if (watchLaterMovie.id == it.data.id) {
                                it.data.apply {
                                    watchLater = true
                                }
                            }
                        }
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