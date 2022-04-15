package com.rodrigoads.mymovies.data.network

import com.rodrigoads.mymovies.data.network.model.MovieDetailsNetworkResponse
import com.rodrigoads.mymovies.data.network.model.PopularMoviesNetworkResponse
import com.rodrigoads.mymovies.data.network.model.RequestNetworkResponse
import com.rodrigoads.mymovies.data.network.model.SimilarMovieNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ) : RequestNetworkResponse<PopularMoviesNetworkResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ) : MovieDetailsNetworkResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ) : RequestNetworkResponse<SimilarMovieNetworkResponse>
}