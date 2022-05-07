package com.rodrigoads.mymovies.framework.di

import com.rodrigoads.mymovies.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {
    @Binds
    fun bindGetPopularMoviesUseCase(
        getPopularMovies: GetPopularMovies
    ): GetPopularMoviesUseCase

    @Binds
    fun bindGetMovieDetailsUseCase(
        getMovieDetails: GetMovieDetails
    ): GetMovieDetailsUseCase

    @Binds
    fun bindGetSimilarMoviesUseCase(
        getSimilarMovies: GetSimilarMovies
    ): GetSimilarMoviesUseCase

    @Binds
    fun bindMovieCategoriesUseCase(
        getMovieCategories: GetMovieCategories
    ): GetMovieCategoriesUseCase

    @Binds
    fun bindGetMoviesByCategoryUseCase(
        getMoviesByCategory: GetMoviesByCategory
    ): GetMoviesByCategoryUseCase

    @Binds
    fun bindGetMoviesBySearchUseCase(
        getMoviesBySearch: GetMoviesBySearch
    ): GetMoviesBySearchUseCase

    @Binds
    fun bindGetWatchLaterUseCase(
        getWatchLater: GetWatchLater
    ): GetWatchLaterUseCase
}