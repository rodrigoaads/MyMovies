package com.rodrigoads.mymovies.data.network.remote

import com.rodrigoads.mymovies.data.network.TmdbService
import com.rodrigoads.mymovies.data.network.model.RequestNetworkResponse
import com.rodrigoads.mymovies.data.network.model.moviesbysearch.MoviesBySearchNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitMoviesBySearchDataSource @Inject constructor(
    private val tmdbService: TmdbService
) : MoviesBySearchDataSource {
    override suspend fun getMoviesBySearch(
        query: String,
        page: Int
    ): RequestNetworkResponse<MoviesBySearchNetworkResponse> {
        return withContext(Dispatchers.IO) {
            tmdbService.getMoviesBySearch(query, page)
        }
    }
}

interface MoviesBySearchDataSource {
    suspend fun getMoviesBySearch(
        query: String,
        page: Int
    ): RequestNetworkResponse<MoviesBySearchNetworkResponse>
}