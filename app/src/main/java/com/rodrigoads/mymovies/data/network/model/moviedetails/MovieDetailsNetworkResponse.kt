package com.rodrigoads.mymovies.data.network.model.moviedetails

import com.rodrigoads.mymovies.domain.model.MovieDetails

data class MovieDetailsNetworkResponse(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: Any?,
    val budget: Int,
    val genres: List<GenresNetworkResponse>,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val original_language: String,
    val original_title: String,
    val overview: String?,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompaniesNetworkResponse>,
    val production_countries: List<ProductionCountriesNetworkResponse>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguagesNetworkResponse>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailsNetworkResponse.toMovieDetails() : MovieDetails {
    return MovieDetails(
        backdrop_path = this.backdrop_path,
        id = this.id,
        imdb_id = this.imdb_id,
        overview = this.overview,
        poster_path = this.poster_path,
        release_date = this.release_date,
        runtime = this.runtime,
        title = this.title,
        vote_average = this.vote_average
    )
}
