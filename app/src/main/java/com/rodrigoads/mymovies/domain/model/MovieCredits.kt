package com.rodrigoads.mymovies.domain.model

import com.rodrigoads.mymovies.presenter.details.model.MovieCreditsUiModel

data class MovieCredits(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

fun MovieCredits.toMovieCreditsUiModel() : MovieCreditsUiModel {
    return MovieCreditsUiModel(
        id = this.id,
        cast = this.cast.map { it.toCastUiModel() },
        crew = this.crew.map { it.toCrewUiModel() }
    )
}
