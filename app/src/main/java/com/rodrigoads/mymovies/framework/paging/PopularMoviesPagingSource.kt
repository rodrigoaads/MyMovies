package com.rodrigoads.mymovies.framework.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.model.popularmovies.toPopularMovies
import com.rodrigoads.mymovies.data.network.remote.PopularMoviesDataSource
import com.rodrigoads.mymovies.domain.model.PopularMovies

class PopularMoviesPagingSource(
    private val popularMoviesDataSource: PopularMoviesDataSource,
    private val firstItem: (LiveData<ResultStatus<PopularMovies?>>) -> Unit
) : PagingSource<Int, PopularMovies>() {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovies> {
        return try {
            val page = params.key ?: 1
            val request = popularMoviesDataSource.getPopularMovies(page)

            if (page == 1) {
                val firstRequestItem = request.results[0].toPopularMovies()
                firstItem(
                    liveData {
                        emit(ResultStatus.Loading)
                        emit(ResultStatus.Success(firstRequestItem))
                    })
                request.results.removeAt(0)
            }

            LoadResult.Page(
                data = request.results.map { it.toPopularMovies() },
                prevKey = null,
                nextKey = if (page < PAGE_LIMIT) {
                    page + NEXT_PAGE
                } else null
            )
        } catch (e: Exception) {
            if (params.key == 1) {
                firstItem(liveData {
                    emit(ResultStatus.Error(e))
                })
            }
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularMovies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(NEXT_PAGE) ?: anchorPage?.nextKey?.minus(NEXT_PAGE)
        }
    }

    companion object {
        const val PAGE_LIMIT = 5
        const val NEXT_PAGE = 1
    }
}