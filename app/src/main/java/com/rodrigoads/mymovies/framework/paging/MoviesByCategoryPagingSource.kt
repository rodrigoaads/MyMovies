package com.rodrigoads.mymovies.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rodrigoads.mymovies.data.network.model.moviesbycategory.toMoviesByCategory
import com.rodrigoads.mymovies.data.network.remote.MoviesByCategoryDataSource
import com.rodrigoads.mymovies.domain.model.MoviesByCategory

class MoviesByCategoryPagingSource(
    private val moviesByCategoryDataSource: MoviesByCategoryDataSource,
    private val genre: Int
) : PagingSource<Int, MoviesByCategory>() {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesByCategory> {
        return try {
            val page = params.key ?: 1

            val request = moviesByCategoryDataSource.getMoviesByCategory(page, genre)

            LoadResult.Page(
                data = request.results.map { it.toMoviesByCategory() },
                prevKey = null,
                nextKey = if(page != request.total_pages){
                    page + NEXT_PAGE
                }else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesByCategory>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.minus(NEXT_PAGE) ?: anchorPage?.nextKey?.plus(NEXT_PAGE)
        }
    }

    companion object {
        const val NEXT_PAGE = 1
    }
}