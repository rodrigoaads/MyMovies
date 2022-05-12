package com.rodrigoads.mymovies.presenter.bycategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.rodrigoads.mymovies.databinding.FragmentMoviesByCategoryBinding
import com.rodrigoads.mymovies.presenter.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesByCategoryFragment : Fragment() {
    private lateinit var moviesByCategoryBinding: FragmentMoviesByCategoryBinding
    private val moviesByCategoryViewModel: MoviesByCategoryViewModel by viewModels()
    private val args: MoviesByCategoryFragmentArgs by navArgs()
    private lateinit var moviesByCategoryAdapter: MoviesByCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesByCategoryBinding =
            FragmentMoviesByCategoryBinding.inflate(inflater, container, false)
        return moviesByCategoryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoviesByCategoryAdapter()
        observeInitialLoadState()

        moviesByCategoryViewModel.moviesByCategory.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                moviesByCategoryAdapter.submitData(it)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = args.name
        if (moviesByCategoryViewModel.moviesByCategory.value == null) {
            moviesByCategoryViewModel.getMoviesByCategory(args.id)
        }
    }

    private fun initMoviesByCategoryAdapter() {
        moviesByCategoryAdapter = MoviesByCategoryAdapter {
            val action = MoviesByCategoryFragmentDirections
                .actionMoviesByCategoryFragmentToMovieDetailsFragment(id = it.id)
            findNavController().navigate(action)
        }

        val flexLayoutManager = FlexboxLayoutManager(context).apply {
            alignItems = AlignItems.CENTER
            justifyContent = JustifyContent.SPACE_AROUND

        }

        moviesByCategoryBinding.recyclerViewMoviesByCategory.apply {
            adapter = moviesByCategoryAdapter.withLoadStateFooter(
                MoviesByCategoryLoadStateAdapter{
                    moviesByCategoryAdapter.retry()
                }
            )
            layoutManager = flexLayoutManager
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            moviesByCategoryAdapter.loadStateFlow.collect {
                moviesByCategoryBinding.viewFlipperMoviesByCategory.displayedChild =
                    when (it.refresh) {
                        is LoadState.Loading -> {
                            FLIPPER_CHILD_MOVIES_BY_CATEGORY_LOADING_STATE
                        }
                        is LoadState.NotLoading -> {
                            FLIPPER_CHILD_MOVIES_BY_CATEGORY
                        }
                        is LoadState.Error -> {
                            moviesByCategoryBinding.includeViewMoviesErrorState.buttonPopularMoviesTryAgain.apply {
                                isVisible = false
                            }
                            FLIPPER_CHILD_MOVIES_BY_CATEGORY_ERROR_STATE
                        }
                    }
            }
        }
    }

    companion object {
        const val FLIPPER_CHILD_MOVIES_BY_CATEGORY_LOADING_STATE = 0
        const val FLIPPER_CHILD_MOVIES_BY_CATEGORY = 1
        const val FLIPPER_CHILD_MOVIES_BY_CATEGORY_ERROR_STATE = 2
    }
}