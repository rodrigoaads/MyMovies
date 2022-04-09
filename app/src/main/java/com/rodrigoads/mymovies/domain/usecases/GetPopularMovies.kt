package com.rodrigoads.mymovies.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rodrigoads.mymovies.data.repositories.PopularMoviesRepository
import com.rodrigoads.mymovies.domain.model.PopularMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMovies @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) : GetPopularMoviesUseCase {
    override fun invoke(
        pagingConfig: PagingConfig,
        firstItem: (PopularMovies?) -> Unit
    ): Flow<PagingData<PopularMovies>> {
        return Pager(config = pagingConfig) {
            popularMoviesRepository.getRemotePopularMovies(firstItem)
        }.flow
    }

}

interface GetPopularMoviesUseCase {
    operator fun invoke(
        pagingConfig: PagingConfig,
        firstItem: (PopularMovies?) -> Unit
    ): Flow<PagingData<PopularMovies>>
}