package com.rodrigoads.mymovies.presenter.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.rodrigoads.mymovies.databinding.MoviesLoadMoreStateLayoutBinding

class PopularMoviesLoadStateViewHolder(
    popularMoviesLoadMoreStateLayoutBinding: MoviesLoadMoreStateLayoutBinding,
    retry : () -> Unit
) : RecyclerView.ViewHolder(popularMoviesLoadMoreStateLayoutBinding.root) {

    private val progressBar = popularMoviesLoadMoreStateLayoutBinding.progressBarLoadStateLoading
    private val textViewError = popularMoviesLoadMoreStateLayoutBinding.textViewLoadStateError.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState){
        progressBar.isVisible = loadState is LoadState.Loading
        textViewError.isVisible = loadState is LoadState.Error
    }

    companion object{
        fun create(parent: ViewGroup, retry : () -> Unit): PopularMoviesLoadStateViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val item = MoviesLoadMoreStateLayoutBinding.inflate(inflater, parent, false)
            return PopularMoviesLoadStateViewHolder(item, retry)
        }
    }
}