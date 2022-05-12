package com.rodrigoads.mymovies.domain.usecases

import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.credits.toMovieCredits
import com.rodrigoads.mymovies.data.repositories.MovieCreditsRepository
import com.rodrigoads.mymovies.domain.model.MovieCredits
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieCredits @Inject constructor(
    private val getMovieCreditsRepository: MovieCreditsRepository
) : GetMovieCreditsUseCase {
    override suspend fun getMovieCredits(id: Int): Flow<ResultStatus<MovieCredits>> {
        return getMovieCreditsRepository.getRemoteMovieCredits(id).map { resultStatus ->
            when(resultStatus){
                is ResultStatus.Success -> {
                    ResultStatus.Success(data = resultStatus.data.toMovieCredits())
                }
                is ResultStatus.Error -> {
                    ResultStatus.Error(resultStatus.e)
                }
                is ResultStatus.Loading -> {
                    ResultStatus.Loading
                }
            }
        }
    }
}

interface GetMovieCreditsUseCase {
    suspend fun getMovieCredits(id: Int) : Flow<ResultStatus<MovieCredits>>
}