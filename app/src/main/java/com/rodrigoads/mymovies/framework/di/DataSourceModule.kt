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
    ) : PopularMoviesDataSource

    @Binds
    fun bindMovieDetailsDataSource(
        retrofitMovieDetailsDataSource: RetrofitMovieDetailsDataSource
    ): MovieDetailsDataSource

    @Binds
    fun bindSimilarMoviesDataSource(
        retrofitSimilarMoviesDataSource: RetrofitSimilarMoviesDataSource
    ) : SimilarMoviesDataSource

}