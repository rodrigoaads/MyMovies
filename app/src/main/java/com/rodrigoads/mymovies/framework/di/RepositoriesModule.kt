package com.rodrigoads.mymovies.framework.di

import com.rodrigoads.mymovies.data.network.remote.PopularMoviesRemoteDataSource
import com.rodrigoads.mymovies.data.network.remote.RetrofitPopularMoviesDataSource
import com.rodrigoads.mymovies.data.repositories.PopularMoviesRepository
import com.rodrigoads.mymovies.data.repositories.PopularMoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindPopularMoviesDataSource(
        retrofitPopularMoviesDataSource: RetrofitPopularMoviesDataSource
    ) : PopularMoviesRemoteDataSource

    @Binds
    fun bindPopularMoviesRepository(
        popularMoviesRepositoryImpl: PopularMoviesRepositoryImpl
    ): PopularMoviesRepository
}