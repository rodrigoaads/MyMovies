package com.rodrigoads.mymovies.data.network.remote

import com.rodrigoads.mymovies.data.network.TmdbService
import com.rodrigoads.mymovies.data.network.model.RequestNetworkResponse
import com.rodrigoads.mymovies.data.network.model.similarmovies.SimilarMovieNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitSimilarMoviesDataSource @Inject constructor(
    private val tmdbService: TmdbService
) : SimilarMoviesDataSource {
    override suspend fun getSimilarMovies(
        id: Int,
        page: Int
    ): RequestNetworkResponse<SimilarMovieNetworkResponse> {
        return withContext(Dispatchers.IO) {
            tmdbService.getSimilarMovies(id, page)
        }
    }
}

interface SimilarMoviesDataSource {
    suspend fun getSimilarMovies(
        id: Int,
        page: Int
    ): RequestNetworkResponse<SimilarMovieNetworkResponse>
}