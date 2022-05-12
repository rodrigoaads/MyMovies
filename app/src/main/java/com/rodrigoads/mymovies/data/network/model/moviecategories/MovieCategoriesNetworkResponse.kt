package com.rodrigoads.mymovies.data.network.model.moviecategories

import com.rodrigoads.mymovies.data.network.model.moviedetails.GenresNetworkResponse
import com.rodrigoads.mymovies.data.network.model.moviedetails.toMovieGenres
import com.rodrigoads.mymovies.domain.model.MovieCategories

data class MovieCategoriesNetworkResponse(
    val genres: List<GenresNetworkResponse>
)

fun MovieCategoriesNetworkResponse.toMovieGenres(): MovieCategories {
    return MovieCategories(
        genres = genres.map {
            it.toMovieGenres()
        })
}
