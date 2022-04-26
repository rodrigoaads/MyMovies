package com.rodrigoads.mymovies.data.network

import com.rodrigoads.mymovies.data.network.model.RequestNetworkResponse
import com.rodrigoads.mymovies.data.network.model.moviecategories.MovieCategoriesNetworkResponse
import com.rodrigoads.mymovies.data.network.model.moviedetails.MovieDetailsNetworkResponse
import com.rodrigoads.mymovies.data.network.model.moviesbycategory.MoviesByCategoryNetworkResponse
import com.rodrigoads.mymovies.data.network.model.popularmovies.PopularMoviesNetworkResponse
import com.rodrigoads.mymovies.data.network.model.similarmovies.SimilarMovieNetworkResponse
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

    @GET("genre/movie/list")
    suspend fun getCategories() : MovieCategoriesNetworkResponse

    @GET("discover/movie")
    suspend fun getMoviesByCategory(
        @Query("page") page: Int,
        @Query("with_genres") withGenres: Int
    ): RequestNetworkResponse<MoviesByCategoryNetworkResponse>
}