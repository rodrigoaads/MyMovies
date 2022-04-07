package com.rodrigoads.mymovies.data.network

import retrofit2.http.GET

interface TmdbService {
    @GET("movie/popular")
    fun getPopularMovies()
}