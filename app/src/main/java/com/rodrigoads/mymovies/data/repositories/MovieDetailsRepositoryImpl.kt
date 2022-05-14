package com.rodrigoads.mymovies.data.repositories

import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.moviedetails.MovieDetailsNetworkResponse
import com.rodrigoads.mymovies.data.network.remote.MovieDetailsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val movieDetailsDataSource: MovieDetailsDataSource
) : MovieDetailsRepository {
    override suspend fun getRemoteMovieDetails(id: Int): Flow<ResultStatus<MovieDetailsNetworkResponse>> {
        return movieDetailsDataSource.getMovieDetails(id)
    }
}

interface MovieDetailsRepository {
    suspend fun getRemoteMovieDetails(id: Int): Flow<ResultStatus<MovieDetailsNetworkResponse>>
}