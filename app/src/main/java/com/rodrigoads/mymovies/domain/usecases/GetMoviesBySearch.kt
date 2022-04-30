package com.rodrigoads.mymovies.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rodrigoads.mymovies.data.repositories.MoviesBySearchRepository
import com.rodrigoads.mymovies.domain.model.MoviesBySearch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesBySearch @Inject constructor(
    private val moviesBySearchRepository: MoviesBySearchRepository
) : GetMoviesBySearchUseCase {
    override fun invoke(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MoviesBySearch>> {
        return Pager(
            config = pagingConfig
        ) {
            moviesBySearchRepository.getMoviesBySearch(query)
        }.flow
    }
}

interface GetMoviesBySearchUseCase {
    operator fun invoke(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MoviesBySearch>>
}