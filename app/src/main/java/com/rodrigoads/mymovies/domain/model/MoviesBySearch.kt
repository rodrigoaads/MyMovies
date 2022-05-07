package com.rodrigoads.mymovies.domain.model

import com.rodrigoads.mymovies.presenter.search.model.SearchMovieUiModel

data class MoviesBySearch(
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
    val vote_average: Double?,
    var formattedDate: String? = null
){
    init {
        @Suppress("MagicNumber")
        formattedDate = release_date?.let{
            if(it.isNotEmpty()){
                release_date.substring(0..3)
            }else null
        }
    }
}

fun MoviesBySearch.toMoviesBySearchUiModel() : SearchMovieUiModel {
    return SearchMovieUiModel(
        poster_path = this.poster_path,
        title = this.title,
        formattedDate = this.formattedDate,
        id = this.id,
        vote_average = this.vote_average
    )
}
