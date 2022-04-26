package com.rodrigoads.mymovies.data.network.remote

import com.rodrigoads.mymovies.data.network.TmdbService
import com.rodrigoads.mymovies.data.network.model.RequestNetworkResponse
import com.rodrigoads.mymovies.data.network.model.moviesbycategory.MoviesByCategoryNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitMoviesByCategoryDataSource @Inject constructor(
    private val tmdbService: TmdbService
) : MoviesByCategoryDataSource {
    override suspend fun getMoviesByCategory(
        page: Int,
        genre: Int
    ): RequestNetworkResponse<MoviesByCategoryNetworkResponse> {
        return withContext(Dispatchers.IO) {
            tmdbService.getMoviesByCategory(page, genre)
        }
    }
}

interface MoviesByCategoryDataSource {
    suspend fun getMoviesByCategory(
        page: Int,
        genre: Int
    ): RequestNetworkResponse<MoviesByCategoryNetworkResponse>
}