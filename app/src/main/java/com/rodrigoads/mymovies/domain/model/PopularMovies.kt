package com.rodrigoads.mymovies.domain.model

import com.rodrigoads.mymovies.presenter.popular.model.PopularMoviesUiModel

data class PopularMovies(
    val poster_path: String?,
    val adult: Boolean?,
    val overview: String?,
    val release_date: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String?,
    val original_language: String?,
    val title: String?,
    val backdrop_path: String?,
    val popularity: Double?,
    val vote_count: Int?,
    val video: Boolean?,
    val vote_average: Double?
)

fun PopularMovies.toPopularMoviesUiModel(): PopularMoviesUiModel {
    return PopularMoviesUiModel(
        poster_path = this.poster_path,
        title = this.title,
        id = this.id,
        overview = this.overview,
        backdrop_path = this.backdrop_path
    )
}
