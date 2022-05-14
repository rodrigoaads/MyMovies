package com.rodrigoads.mymovies.presenter.categories

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodrigoads.mymovies.presenter.categories.model.CategoriesUiModel

class CategoriesAdapter(
    private val categoryList: List<CategoriesUiModel>,
    private val onClick: (CategoriesUiModel) -> Unit
) : RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}