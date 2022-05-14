package com.rodrigoads.mymovies.presenter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.rodrigoads.mymovies.databinding.MoviesLoadMoreStateLargeLayoutBinding

class SearchMoviesLoadStateViewHolder(
    searchMoviesLoadMoreState: MoviesLoadMoreStateLargeLayoutBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(searchMoviesLoadMoreState.root) {

    private val progressBar = searchMoviesLoadMoreState.progressBarLargeLoadStateLoading
    private val textViewError = searchMoviesLoadMoreState.textViewLargeLoadStateError.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        textViewError.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): SearchMoviesLoadStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val item = MoviesLoadMoreStateLargeLayoutBinding.inflate(inflater, parent, false)
            return SearchMoviesLoadStateViewHolder(item, retry)
        }
    }
}