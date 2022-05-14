package com.rodrigoads.mymovies.framework.di

import com.rodrigoads.mymovies.data.network.remote.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindPopularMoviesDataSource(
        retrofitPopularMoviesDataSource: RetrofitPopularMoviesDataSource
    ): PopularMoviesDataSource

    @Binds
    fun bindMovieDetailsDataSource(
        retrofitMovieDetailsDataSource: RetrofitMovieDetailsDataSource
    ): MovieDetailsDataSource

    @Binds
    fun bindSimilarMoviesDataSource(
        retrofitSimilarMoviesDataSource: RetrofitSimilarMoviesDataSource
    ): SimilarMoviesDataSource

    @Binds
    fun bindMovieCategoriesDataSource(
        retrofitMovieCategoriesDataSource: RetrofitMovieCategoriesDataSource
    ): MovieCategoriesDataSource

    @Binds
    fun bindMoviesByCategoryDataSource(
        retrofitMoviesByCategoryDataSource: RetrofitMoviesByCategoryDataSource
    ): MoviesByCategoryDataSource

    @Binds
    fun bindMoviesBySearchDataSource(
        retrofitMoviesBySearchDataSource: RetrofitMoviesBySearchDataSource
    ): MoviesBySearchDataSource

    @Binds
    fun bindMovieCreditsDataSource(
        retrofitMovieCreditsDataSource: RetrofitMovieCreditsDataSource
    ): MovieCreditsDataSource
}