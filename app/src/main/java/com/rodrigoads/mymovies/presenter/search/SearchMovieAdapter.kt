package com.rodrigoads.mymovies.presenter.search

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rodrigoads.mymovies.presenter.search.model.SearchMovieUiModel

class SearchMovieAdapter(
    private val onClick: (SearchMovieUiModel) -> Unit
) : PagingDataAdapter<SearchMovieUiModel, SearchMovieViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<SearchMovieUiModel>(){
            override fun areItemsTheSame(
                oldItem: SearchMovieUiModel,
                newItem: SearchMovieUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SearchMovieUiModel,
                newItem: SearchMovieUiModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}