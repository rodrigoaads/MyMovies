package com.rodrigoads.mymovies.presenter.popular

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rodrigoads.mymovies.presenter.popular.model.PopularMoviesUiModel

class PopularMoviesAdapter(
    private val onClickItem : (PopularMoviesUiModel) -> Unit
) : PagingDataAdapter<PopularMoviesUiModel, PopularMoviesViewHolder>(diffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder.create(parent, onClickItem)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        getItem(position)?.let {
                holder.bind(it)
        }
    }

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<PopularMoviesUiModel>(){
            override fun areItemsTheSame(
                oldItem: PopularMoviesUiModel,
                newItem: PopularMoviesUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PopularMoviesUiModel,
                newItem: PopularMoviesUiModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}