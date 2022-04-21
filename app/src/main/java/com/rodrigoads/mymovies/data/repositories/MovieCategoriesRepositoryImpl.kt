package com.rodrigoads.mymovies.data.repositories

import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.moviecategories.MovieCategoriesNetworkResponse
import com.rodrigoads.mymovies.data.network.remote.MovieCategoriesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieCategoriesRepositoryImpl @Inject constructor(
    private val movieCategoriesDataSource: MovieCategoriesDataSource
): MovieCategoriesRepository {
    override suspend fun getRemoteMovieCategories(): Flow<ResultStatus<MovieCategoriesNetworkResponse>> {
        return movieCategoriesDataSource.getMovieCategories()
    }
}

interface MovieCategoriesRepository {
    suspend fun getRemoteMovieCategories() : Flow<ResultStatus<MovieCategoriesNetworkResponse>>
}