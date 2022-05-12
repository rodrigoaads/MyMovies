package com.rodrigoads.mymovies.data.repositories

import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.credits.MovieCreditsNetworkResponse
import com.rodrigoads.mymovies.data.network.remote.MovieCreditsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieCreditsRepositoryImpl @Inject constructor(
    private val movieCreditsDataSource: MovieCreditsDataSource
): MovieCreditsRepository {
    override suspend fun getRemoteMovieCredits(id: Int): Flow<ResultStatus<MovieCreditsNetworkResponse>> {
        return movieCreditsDataSource.getMovieCredits(id)
    }
}

interface MovieCreditsRepository {
    suspend fun getRemoteMovieCredits(id: Int) : Flow<ResultStatus<MovieCreditsNetworkResponse>>
}