package com.rodrigoads.mymovies.presenter.details.model


data class MovieCreditsUiModel(
    val id: Int,
    val cast: List<CastUiModel>,
    val crew: List<CrewUiModel>
)