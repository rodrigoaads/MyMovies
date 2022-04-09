package com.rodrigoads.mymovies.framework.di

import com.rodrigoads.mymovies.domain.usecases.GetPopularMovies
import com.rodrigoads.mymovies.domain.usecases.GetPopularMoviesUseCase
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
}