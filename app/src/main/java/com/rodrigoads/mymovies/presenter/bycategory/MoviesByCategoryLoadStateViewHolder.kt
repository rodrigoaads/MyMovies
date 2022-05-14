package com.rodrigoads.mymovies.presenter.bycategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.rodrigoads.mymovies.databinding.MoviesLoadMoreStateLargeLayoutBinding

class MoviesByCategoryLoadStateViewHolder(
    moviesByCategoryLoadMoreStateLargeLayoutBinding: MoviesLoadMoreStateLargeLayoutBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(moviesByCategoryLoadMoreStateLargeLayoutBinding.root) {

    private val progressBar =
        moviesByCategoryLoadMoreStateLargeLayoutBinding.progressBarLargeLoadStateLoading
    private val textViewError =
        moviesByCategoryLoadMoreStateLargeLayoutBinding.textViewLargeLoadStateError.also {
            it.setOnClickListener {
                retry()
            }
        }

    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        textViewError.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): MoviesByCategoryLoadStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val item = MoviesLoadMoreStateLargeLayoutBinding.inflate(inflater, parent, false)
            return MoviesByCategoryLoadStateViewHolder(item, retry)
        }
    }
}