package com.rodrigoads.mymovies.framework.di

import com.rodrigoads.mymovies.data.repositories.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {
    @Binds
    fun bindPopularMoviesRepository(
        popularMoviesRepositoryImpl: PopularMoviesRepositoryImpl
    ): PopularMoviesRepository

    @Binds
    fun bindMovieDetailsRepository(
        movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

    @Binds
    fun bindSimilarMoviesRepository(
        similarMoviesRepositoryImpl: SimilarMoviesRepositoryImpl
    ): SimilarMoviesRepository

    @Binds
    fun bindMovieCategoriesRepository(
        movieCategoriesRepositoryImpl: MovieCategoriesRepositoryImpl
    ): MovieCategoriesRepository

    @Binds
    fun bindMoviesByCategoryRepository(
        moviesByCategoryRepositoryImpl: MoviesByCategoryRepositoryImpl
    ): MoviesByCategoryRepository

    @Binds
    fun bindMoviesBySearchRepository(
        moviesBySearchRepositoryImpl: MoviesBySearchRepositoryImpl
    ): MoviesBySearchRepository

    @Binds
    fun bindWatchLaterRepository(
        watchLaterRepositoryImpl: WatchLaterRepositoryImpl
    ): WatchLaterRepository

    @Binds
    fun bindMovieCreditsRepository(
        movieCreditsRepositoryImpl: MovieCreditsRepositoryImpl
    ): MovieCreditsRepository
}