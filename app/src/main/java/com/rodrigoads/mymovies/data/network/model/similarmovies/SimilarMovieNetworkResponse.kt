package com.rodrigoads.mymovies.data.network.model.similarmovies

import com.rodrigoads.mymovies.domain.model.SimilarMovies

data class SimilarMovieNetworkResponse(
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

fun SimilarMovieNetworkResponse.toSimilarMovies(): SimilarMovies {
    return SimilarMovies(
        poster_path = this.poster_path,
        adult = this.adult,
        overview = this.overview,
        release_date = this.release_date,
        genre_ids = this.genre_ids,
        id = this.id,
        original_title = this.original_title,
        original_language = this.original_language,
        title = this.title,
        backdrop_path = this.backdrop_path,
        popularity = this.popularity,
        vote_count = this.vote_count,
        video = this.video,
        vote_average = this.vote_average
    )
}
