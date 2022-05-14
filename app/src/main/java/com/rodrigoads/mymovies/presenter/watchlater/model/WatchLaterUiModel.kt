package com.rodrigoads.mymovies.presenter.watchlater.model

import com.rodrigoads.mymovies.domain.model.WatchLater

data class WatchLaterUiModel(
    val position: Int? = null,
    val id: Int,
    val title: String?,
    val poster_path: String?,
    val vote_average: Double?,
    var formattedDate: String?,
)

fun WatchLaterUiModel.toWatchLater(): WatchLater {
    return WatchLater(
        position = this.position,
        id = this.id,
        title = this.title,
        poster_path = this.poster_path,
        vote_average = this.vote_average,
        formattedDate = this.formattedDate,
    )
}
