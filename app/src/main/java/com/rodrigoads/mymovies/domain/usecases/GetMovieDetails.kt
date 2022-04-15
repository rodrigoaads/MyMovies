package com.rodrigoads.mymovies.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.toMovieDetails
import com.rodrigoads.mymovies.data.repositories.MovieDetailsRepository
import com.rodrigoads.mymovies.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieDetails @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
): GetMovieDetailsUseCase {
    override suspend fun invoke(id: Int) : Flow<ResultStatus<MovieDetails>> {
        return movieDetailsRepository.getRemoteMovieDetails(id).map {
            when(it) {
                is ResultStatus.Success -> {
                    ResultStatus.Success(it.data.toMovieDetails())
                }
                is ResultStatus.Error -> {
                    ResultStatus.Error(it.e)
                }
                is ResultStatus.Loading -> {
                    ResultStatus.Loading
                }
            }
        }

    }
}

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(id: Int) : Flow<ResultStatus<MovieDetails>>
}