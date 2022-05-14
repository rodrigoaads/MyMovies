package com.rodrigoads.mymovies.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rodrigoads.mymovies.data.repositories.SimilarMoviesRepository
import com.rodrigoads.mymovies.domain.model.SimilarMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarMovies @Inject constructor(
    private val similarMoviesRepository: SimilarMoviesRepository
) : GetSimilarMoviesUseCase {
    override fun invoke(id: Int, pagingConfig: PagingConfig): Flow<PagingData<SimilarMovies>> {
        return Pager(config = pagingConfig) {
            similarMoviesRepository.getRemoteSimilarMovies(id)
        }.flow
    }

}

interface GetSimilarMoviesUseCase {
    operator fun invoke(id: Int, pagingConfig: PagingConfig): Flow<PagingData<SimilarMovies>>
}