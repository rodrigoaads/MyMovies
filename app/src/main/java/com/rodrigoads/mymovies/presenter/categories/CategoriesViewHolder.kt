package com.rodrigoads.mymovies.presenter.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodrigoads.mymovies.databinding.CategoryItemLayoutBinding
import com.rodrigoads.mymovies.presenter.categories.model.CategoriesUiModel

class CategoriesViewHolder(
    categoryItemLayoutBinding: CategoryItemLayoutBinding,
    private val onClick : (CategoriesUiModel) -> Unit
) : RecyclerView.ViewHolder(categoryItemLayoutBinding.root) {

    private val textViewCategoryName = categoryItemLayoutBinding.textViewCategoryName

    fun bind(categoryUiModel: CategoriesUiModel){
        textViewCategoryName.text = categoryUiModel.name

        itemView.setOnClickListener {
            onClick(categoryUiModel)
        }
    }

    companion object{
        fun create(parent: ViewGroup, onClick : (CategoriesUiModel) -> Unit) : CategoriesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val item = CategoryItemLayoutBinding.inflate(inflater, parent, false)
            return CategoriesViewHolder(item, onClick)
        }
    }
}