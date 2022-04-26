package com.rodrigoads.mymovies.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rodrigoads.mymovies.data.repositories.MoviesByCategoryRepository
import com.rodrigoads.mymovies.domain.model.MoviesByCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesByCategory @Inject constructor(
    private val moviesByCategoriesRepository: MoviesByCategoryRepository
): GetMoviesByCategoryUseCase {
    override fun invoke(genre: Int, pagingConfig: PagingConfig): Flow<PagingData<MoviesByCategory>> {
        return Pager(
            config = pagingConfig,
        ){
            moviesByCategoriesRepository.getRemoteMoviesByCategory(genre)
        }.flow
    }
}

interface GetMoviesByCategoryUseCase {
    operator fun invoke(genre: Int, pagingConfig: PagingConfig) : Flow<PagingData<MoviesByCategory>>
}