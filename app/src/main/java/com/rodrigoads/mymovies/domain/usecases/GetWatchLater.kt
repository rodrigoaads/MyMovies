package com.rodrigoads.mymovies.domain.usecases

import com.rodrigoads.mymovies.data.local.entities.toWatchLater
import com.rodrigoads.mymovies.data.repositories.WatchLaterRepository
import com.rodrigoads.mymovies.domain.model.WatchLater
import com.rodrigoads.mymovies.domain.model.toWatchLaterEntity
import com.rodrigoads.mymovies.presenter.watchlater.model.WatchLaterUiModel
import com.rodrigoads.mymovies.presenter.watchlater.model.toWatchLater
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWatchLater @Inject constructor(
    private val watchLaterRepository: WatchLaterRepository
) : GetWatchLaterUseCase {
    override fun getAllWatchLaterMovies(): Flow<List<WatchLater>> {
        return watchLaterRepository.getAllWatchLaterMovies()
            .map { list -> list.map { it.toWatchLater() } }
    }

    override suspend fun insertWatchLaterMovie(watchLaterUiModel: WatchLaterUiModel) {
        watchLaterRepository.insertWatchLaterMovie(watchLaterUiModel.toWatchLater())
    }

    override suspend fun removeWatchLaterMovie(id: Int) {
        watchLaterRepository.removeWatchLaterMovie(id)
    }
}

interface GetWatchLaterUseCase {
    fun getAllWatchLaterMovies(): Flow<List<WatchLater>>

    suspend fun insertWatchLaterMovie(watchLaterUiModel: WatchLaterUiModel)

    suspend fun removeWatchLaterMovie(id: Int)
}