package com.rodrigoads.mymovies.domain.model

import com.rodrigoads.mymovies.data.local.entities.WatchLaterEntity
import com.rodrigoads.mymovies.presenter.watchlater.model.WatchLaterUiModel

data class WatchLater(
    val position : Int?,
    val id: Int,
    val title: String?,
    val poster_path: String?,
    val vote_average: Double?,
    var formattedDate: String?,
)

fun WatchLater.toWatchLaterEntity() : WatchLaterEntity {
    return WatchLaterEntity(
        id = this.id,
        title = this.title,
        poster_path = this.poster_path,
        vote_average = this.vote_average,
        formattedDate = this.formattedDate
    )
}

fun WatchLater.toWatchLaterUiModel() : WatchLaterUiModel {
    return WatchLaterUiModel(
        position = this.position,
        id = this.id,
        title = this.title,
        poster_path = this.poster_path,
        vote_average = this.vote_average,
        formattedDate = this.formattedDate,
    )
}
