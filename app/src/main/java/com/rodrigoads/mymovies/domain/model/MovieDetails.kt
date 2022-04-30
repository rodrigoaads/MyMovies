package com.rodrigoads.mymovies.domain.model

import com.rodrigoads.mymovies.presenter.details.model.MovieDetailsUiModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

data class MovieDetails(
    val backdrop_path: String?,
    val id: Int,
    val imdb_id: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val runtime: Int?,
    val title: String?,
    val vote_average: Double?,
    var formattedRuntime: String? = null,
    var formattedDate: String? = null
){
    init {
        this.runtime?.let {
            formattedRuntime = if(it > HOUR){
                "${(it / HOUR)}h ${(it % HOUR)}m"
            }else {
                "${it}Min"
            }
        }

        @Suppress("MagicNumber")
        formattedDate = release_date?.substring(0..3)
    }

    companion object {
        private const val HOUR = 60
    }
}

fun MovieDetails.toMovieDetailsUiModel() : MovieDetailsUiModel {
    return MovieDetailsUiModel(
        backdrop_path = this.backdrop_path,
        id = this.id,
        imdb_id = this.imdb_id,
        overview = this.overview,
        poster_path = this.poster_path,
        title = this.title,
        vote_average = this.vote_average,
        formattedDate = this.formattedDate,
        formattedRuntime = this.formattedRuntime
    )
}
