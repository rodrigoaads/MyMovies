package com.rodrigoads.mymovies.presenter.watchlater

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rodrigoads.mymovies.domain.model.toWatchLaterUiModel
import com.rodrigoads.mymovies.domain.usecases.GetWatchLaterUseCase
import com.rodrigoads.mymovies.presenter.details.model.MovieDetailsUiModel
import com.rodrigoads.mymovies.presenter.details.model.toWatchLaterUiModel
import com.rodrigoads.mymovies.presenter.watchlater.model.WatchLaterUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchLaterViewModel @Inject constructor(
    private val getWatchLaterUseCase: GetWatchLaterUseCase,
) : ViewModel() {

    val watchLaterList: LiveData<List<WatchLaterUiModel>> =
        getWatchLaterUseCase.getAllWatchLaterMovies()
            .map { list -> list.map { it.toWatchLaterUiModel() } }.asLiveData()


    suspend fun managementWatchLaterMovie(movieDetailsUiModel: MovieDetailsUiModel): Boolean {
        return if (movieDetailsUiModel.watchLater) {
            getWatchLaterUseCase.removeWatchLaterMovie(movieDetailsUiModel.id)
            false
        } else {
            getWatchLaterUseCase.insertWatchLaterMovie(movieDetailsUiModel.toWatchLaterUiModel())
            true
        }
    }

    suspend fun removeWatchLater(id: Int) {
        getWatchLaterUseCase.removeWatchLaterMovie(id)
    }

    fun checkWatchLaterMovieStatus(id: Int): Boolean {
        var isWatchLaterMovie = false
        watchLaterList.value?.forEach {
            if (it.id == id) {
                isWatchLaterMovie = true
            }
        }
        return isWatchLaterMovie
    }
}