package com.rodrigoads.mymovies.presenter.watchlater

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rodrigoads.mymovies.presenter.watchlater.model.WatchLaterUiModel

class WatchLaterAdapter(
    private val onClickItem : (Int) -> Unit,
    private val onClickRemoveItem: (Int) -> Unit
) : ListAdapter<WatchLaterUiModel, WatchLaterViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchLaterViewHolder {
        return WatchLaterViewHolder.create(parent, onClickItem, onClickRemoveItem)
    }

    override fun onBindViewHolder(holder: WatchLaterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object{
        private val diffUtil = object : DiffUtil.ItemCallback<WatchLaterUiModel>(){
            override fun areItemsTheSame(
                oldItem: WatchLaterUiModel,
                newItem: WatchLaterUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: WatchLaterUiModel,
                newItem: WatchLaterUiModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}