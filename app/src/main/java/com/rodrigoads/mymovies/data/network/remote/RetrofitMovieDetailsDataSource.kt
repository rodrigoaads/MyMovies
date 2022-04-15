package com.rodrigoads.mymovies.data.network.remote

import com.rodrigoads.mymovies.data.network.TmdbService
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.MovieDetailsNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitMovieDetailsDataSource @Inject constructor(
    private val tmdbService: TmdbService
): MovieDetailsDataSource{
    @Suppress("TooGenericExceptionCaught")
    override suspend fun getMovieDetails(id: Int): Flow<ResultStatus<MovieDetailsNetworkResponse>> {
        return withContext(Dispatchers.IO){
            flow {
                emit(ResultStatus.Loading)
                try {
                    val request = tmdbService.getMovieDetails(id)
                    emit(ResultStatus.Success(request))
                }catch (e: Exception){
                    emit(ResultStatus.Error(e))
                }
            }
        }
    }
}

interface MovieDetailsDataSource {
    suspend fun getMovieDetails(id: Int) : Flow<ResultStatus<MovieDetailsNetworkResponse>>
}