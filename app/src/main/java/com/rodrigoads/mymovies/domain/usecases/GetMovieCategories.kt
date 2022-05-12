package com.rodrigoads.mymovies.domain.usecases

import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.moviecategories.toMovieGenres
import com.rodrigoads.mymovies.data.repositories.MovieCategoriesRepository
import com.rodrigoads.mymovies.domain.model.MovieCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieCategories @Inject constructor(
    private val movieCategoriesRepository: MovieCategoriesRepository
): GetMovieCategoriesUseCase {
    override suspend fun getMovieCategories(): Flow<ResultStatus<MovieCategories>> {
        return movieCategoriesRepository.getRemoteMovieCategories().map {
            when(it){
                is ResultStatus.Success -> {
                    ResultStatus.Success(it.data.toMovieGenres())
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

interface GetMovieCategoriesUseCase {
    suspend fun getMovieCategories() : Flow<ResultStatus<MovieCategories>>
}