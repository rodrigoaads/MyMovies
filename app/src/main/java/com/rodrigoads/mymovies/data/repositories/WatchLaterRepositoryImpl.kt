package com.rodrigoads.mymovies.data.repositories

import com.rodrigoads.mymovies.data.local.daos.WatchLaterDao
import com.rodrigoads.mymovies.data.local.entities.WatchLaterEntity
import com.rodrigoads.mymovies.domain.model.WatchLater
import com.rodrigoads.mymovies.domain.model.toWatchLaterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WatchLaterRepositoryImpl @Inject constructor(
    private val watchLaterDao: WatchLaterDao
) : WatchLaterRepository {
    override fun getAllWatchLaterMovies(): Flow<List<WatchLaterEntity>> {
        return watchLaterDao.getAllWatchLaterMovies()
    }

    override suspend fun insertWatchLaterMovie(watchLater: WatchLater) {
        watchLaterDao.insertWatchLaterMovie(watchLater.toWatchLaterEntity())
    }

    override suspend fun removeWatchLaterMovie(id: Int) {
        watchLaterDao.removeWatchLaterMovie(id)
    }
}

interface WatchLaterRepository {
    fun getAllWatchLaterMovies() : Flow<List<WatchLaterEntity>>

    suspend fun insertWatchLaterMovie(watchLater: WatchLater)

    suspend fun removeWatchLaterMovie(id : Int)
}