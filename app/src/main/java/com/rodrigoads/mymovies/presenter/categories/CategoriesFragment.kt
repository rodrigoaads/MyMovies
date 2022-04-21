package com.rodrigoads.mymovies.presenter.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.FragmentCategoriesBinding
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.categories.model.CategoriesUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private lateinit var categoriesBinding: FragmentCategoriesBinding
    private val categoriesViewModel: CategoriesViewModel by activityViewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoriesBinding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return categoriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesViewModel.categories.observe(viewLifecycleOwner, Observer {
            categoriesBinding.viewFlipperCategories.displayedChild = when(it){
                is ResultUiState.Loading -> {
                    FLIPPER_CHILD_CATEGORIES_LOADING_STATE
                }
                is ResultUiState.Success -> {
                    initAdapter(it.data)
                    FLIPPER_CHILD_CATEGORIES
                }
                is ResultUiState.Error -> {
                    FLIPPER_CHILD_CATEGORIES_ERROR_STATE
                }
            }
        })
    }

    private fun initAdapter(categoryList: List<CategoriesUiModel>) {
        categoriesAdapter = CategoriesAdapter(categoryList){
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        }
        val layoutManagerFlex = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.CENTER
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
        }
        with(categoriesBinding.includeViewCategoryList.recyclerViewCategories){
            adapter = categoriesAdapter
            layoutManager = layoutManagerFlex
        }
    }

    companion object{
        const val FLIPPER_CHILD_CATEGORIES_LOADING_STATE = 0
        const val FLIPPER_CHILD_CATEGORIES = 1
        const val FLIPPER_CHILD_CATEGORIES_ERROR_STATE = 2
        const val SPAN_COUNT = 2
    }
}