package com.rodrigoads.mymovies.data.network.remote

import com.rodrigoads.mymovies.data.network.TmdbService
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.credits.MovieCreditsNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitMovieCreditsDataSource @Inject constructor(
    private val tmdbService: TmdbService
) : MovieCreditsDataSource {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun getMovieCredits(id: Int): Flow<ResultStatus<MovieCreditsNetworkResponse>> {
        return withContext(Dispatchers.IO) {
            flow {
                emit(ResultStatus.Loading)
                try {
                    val request = tmdbService.getMovieCredits(id)
                    emit(ResultStatus.Success(request))
                } catch (e: Exception) {
                    emit(ResultStatus.Error(e))
                }
            }
        }
    }
}

interface MovieCreditsDataSource {
    suspend fun getMovieCredits(id: Int): Flow<ResultStatus<MovieCreditsNetworkResponse>>
}