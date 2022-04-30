package com.rodrigoads.mymovies.presenter.details.model

data class MovieDetailsUiModel(
    val backdrop_path: String?,
    val id: Int,
    val imdb_id: String?,
    val overview: String?,
    val poster_path: String?,
    val title: String?,
    val vote_average: Double?,
    var formattedRuntime: String?,
    var formattedDate: String?
)
