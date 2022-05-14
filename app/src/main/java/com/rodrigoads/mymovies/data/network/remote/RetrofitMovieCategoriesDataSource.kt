package com.rodrigoads.mymovies.data.network.remote

import com.rodrigoads.mymovies.data.network.TmdbService
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.moviecategories.MovieCategoriesNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitMovieCategoriesDataSource @Inject constructor(
    private val tmdbService: TmdbService
) : MovieCategoriesDataSource {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun getMovieCategories(): Flow<ResultStatus<MovieCategoriesNetworkResponse>> {
        return withContext(Dispatchers.IO) {
            flow {
                emit(ResultStatus.Loading)
                try {
                    emit(ResultStatus.Success(tmdbService.getCategories()))
                } catch (e: Exception) {
                    emit(ResultStatus.Error(e))
                }
            }
        }
    }

}

interface MovieCategoriesDataSource {
    suspend fun getMovieCategories(): Flow<ResultStatus<MovieCategoriesNetworkResponse>>
}