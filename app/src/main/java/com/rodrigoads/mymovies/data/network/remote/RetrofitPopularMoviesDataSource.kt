package com.rodrigoads.mymovies.data.network.remote

import com.rodrigoads.mymovies.data.network.TmdbService
import com.rodrigoads.mymovies.data.network.model.RequestNetworkResponse
import com.rodrigoads.mymovies.data.network.model.popularmovies.PopularMoviesNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitPopularMoviesDataSource @Inject constructor(
    private val tmdbService: TmdbService
) : PopularMoviesDataSource {
    override suspend fun getPopularMovies(page: Int): RequestNetworkResponse<PopularMoviesNetworkResponse> {
        return withContext(Dispatchers.IO){
            tmdbService.getPopularMovies(page)
        }
    }
}

interface PopularMoviesDataSource {
    suspend fun getPopularMovies(page: Int) : RequestNetworkResponse<PopularMoviesNetworkResponse>
}