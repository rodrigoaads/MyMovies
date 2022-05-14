package com.rodrigoads.mymovies.presenter.details.model

import com.rodrigoads.mymovies.presenter.watchlater.model.WatchLaterUiModel

data class MovieDetailsUiModel(
    val backdrop_path: String?,
    val id: Int,
    val imdb_id: String?,
    val overview: String?,
    val poster_path: String?,
    val title: String?,
    val vote_average: Double?,
    var formattedRuntime: String?,
    var formattedDate: String?,
    var watchLater: Boolean = false
)

fun MovieDetailsUiModel.toWatchLaterUiModel(): WatchLaterUiModel {
    return WatchLaterUiModel(
        id = this.id,
        title = this.title,
        poster_path = this.poster_path,
        vote_average = this.vote_average,
        formattedDate = this.formattedDate
    )
}
