package com.rodrigoads.mymovies.presenter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.rodrigoads.mymovies.databinding.LoadMoreStateLayoutBinding

class SimilarMoviesLoadStateViewHolder(
    similarMoviesLoadMoreStateLayoutBinding: LoadMoreStateLayoutBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(similarMoviesLoadMoreStateLayoutBinding.root) {

    private val progressBar = similarMoviesLoadMoreStateLayoutBinding.progressBarLoadStateLoading
    private val textViewError =
        similarMoviesLoadMoreStateLayoutBinding.textViewLoadStateError.also {
            it.setOnClickListener {
                retry()
            }
        }

    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        textViewError.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): SimilarMoviesLoadStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val item = LoadMoreStateLayoutBinding.inflate(inflater, parent, false)
            return SimilarMoviesLoadStateViewHolder(item, retry)
        }
    }
}