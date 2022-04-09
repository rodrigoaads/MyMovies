package com.rodrigoads.mymovies.data.network

import com.rodrigoads.mymovies.data.network.model.PopularMoviesNetworkResponse
import com.rodrigoads.mymovies.data.network.model.RequestNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ) : RequestNetworkResponse<PopularMoviesNetworkResponse>
}