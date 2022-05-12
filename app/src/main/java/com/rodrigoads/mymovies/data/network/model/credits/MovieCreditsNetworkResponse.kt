package com.rodrigoads.mymovies.data.network.model.credits

import com.rodrigoads.mymovies.domain.model.MovieCredits

data class MovieCreditsNetworkResponse(
    val id: Int,
    val cast: List<CastNetworkResponse>,
    val crew: List<CrewNetworkResponse>
)

fun MovieCreditsNetworkResponse.toMovieCredits() : MovieCredits {
    return MovieCredits(
        id = this.id,
        cast = this.cast.map { it.toCast() },
        crew = this.crew.map { it.toCrew() }
    )
}
