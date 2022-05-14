package com.rodrigoads.mymovies.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rodrigoads.mymovies.data.network.model.moviesbysearch.toMoviesBySearch
import com.rodrigoads.mymovies.data.network.remote.MoviesBySearchDataSource
import com.rodrigoads.mymovies.domain.model.MoviesBySearch
import kotlin.Exception

class MoviesBySearchPagingSource(
    private val moviesBySearchDataSource: MoviesBySearchDataSource,
    private val query: String
) : PagingSource<Int, MoviesBySearch>() {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesBySearch> {
        return try {
            val page = params.key ?: 1
            val request = moviesBySearchDataSource.getMoviesBySearch(query = query, page = page)

            LoadResult.Page(
                data = request.results.map { it.toMoviesBySearch() },
                prevKey = null,
                nextKey = if (page < request.total_pages) {
                    page + NEXT_PAGE
                } else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesBySearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(NEXT_PAGE) ?: anchorPage?.nextKey?.minus(NEXT_PAGE)
        }
    }

    companion object {
        const val NEXT_PAGE = 1
    }
}