package com.rodrigoads.mymovies.presenter.search.model

data class SearchMovieUiModel(
    val poster_path : String?,
    val title: String?,
    var formattedDate: String?,
    val id: Int,
    val vote_average: Double?
)
