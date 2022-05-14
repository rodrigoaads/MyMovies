package com.rodrigoads.mymovies.data.network.model.moviedetails

import com.rodrigoads.mymovies.domain.model.MovieGenres

data class GenresNetworkResponse(
    val id: Int,
    val name: String?
)

fun GenresNetworkResponse.toMovieGenres(): MovieGenres {
    return MovieGenres(
        id = this.id,
        name = this.name
    )
}
