package com.rodrigoads.mymovies.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rodrigoads.mymovies.data.network.TmdbService
import com.rodrigoads.mymovies.data.network.model.toPopularMovies
import com.rodrigoads.mymovies.data.network.remote.PopularMoviesRemoteDataSource
import com.rodrigoads.mymovies.domain.model.PopularMovies
import javax.inject.Inject

class PopularMoviesPagingSource(
    private val popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource,
    private val firstItem : (PopularMovies?) -> Unit
): PagingSource<Int, PopularMovies>() {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovies> {
        return try {
            val page = params.key ?: 1
            val request = popularMoviesRemoteDataSource.getPopularMovies(page)

            if (page == 1){
                firstItem(request.results[0].toPopularMovies())
            }

            LoadResult.Page(
                data = request.results.map { it.toPopularMovies() },
                prevKey = null,
                nextKey = if(page < PAGE_LIMIT){
                    page + NEXT_PAGE
                }else null
            )
        }catch (e: Exception){
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