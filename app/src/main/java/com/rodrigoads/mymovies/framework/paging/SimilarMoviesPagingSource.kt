package com.rodrigoads.mymovies.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rodrigoads.mymovies.data.network.model.similarmovies.toSimilarMovies
import com.rodrigoads.mymovies.data.network.remote.SimilarMoviesDataSource
import com.rodrigoads.mymovies.domain.model.SimilarMovies
import javax.inject.Inject

class SimilarMoviesPagingSource @Inject constructor(
    private val similarMoviesDataSource: SimilarMoviesDataSource,
    private val movieId: Int
) : PagingSource<Int, SimilarMovies>() {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimilarMovies> {
        return try {
            val page = params.key ?: 1
            val request = similarMoviesDataSource.getSimilarMovies(movieId, page)

            LoadResult.Page(
                data = request.results.map { it.toSimilarMovies() },
                prevKey = null,
                nextKey = if (page < PAGE_LIMIT) {
                    page + NEXT_PAGE
                } else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SimilarMovies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(NEXT_PAGE) ?: anchorPage?.nextKey?.minus(NEXT_PAGE)
        }
    }

    companion object {
        const val PAGE_LIMIT = 3
        const val NEXT_PAGE = 1
    }
}