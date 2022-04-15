package com.rodrigoads.mymovies.presenter.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.filter
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodrigoads.mymovies.databinding.FragmentPopularMoviesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularMoviesListFragment : Fragment() {
    private lateinit var popularMoviesListFragmentBinding: FragmentPopularMoviesListBinding
    private val popularMoviesViewModel: PopularMoviesViewModel by activityViewModels()
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        popularMoviesListFragmentBinding =
            FragmentPopularMoviesListBinding.inflate(inflater, container, false)
        return popularMoviesListFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPopularAdapter()
        observeInitialLoadState()

        popularMoviesViewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                popularMoviesAdapter.submitData(it)
            }
        })
    }

    private fun initPopularAdapter() {
        popularMoviesAdapter = PopularMoviesAdapter {
            val action = PopularMoviesFragmentDirections
                .actionPopularMoviesFragmentToMovieDetailsFragment(it.id)
            findNavController().navigate(action)
        }

        with(popularMoviesListFragmentBinding.popularMoviesRecyclerView) {
            setHasFixedSize(true)
            adapter = popularMoviesAdapter.withLoadStateFooter(
                PopularMoviesLoadStateAdapter{
                    popularMoviesAdapter.retry()
                }
            )
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            popularMoviesAdapter.loadStateFlow.collectLatest { loadState ->
                popularMoviesListFragmentBinding.viewFlipperPopularMovies.displayedChild =
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            FLIPPER_CHILD_POPULAR_MOVIES_LOADING_STATE
                        }
                        is LoadState.NotLoading -> {
                            FLIPPER_CHILD_POPULAR_MOVIES
                        }
                        is LoadState.Error -> {
                            popularMoviesListFragmentBinding
                                .includeViewMoviesErrorState
                                .buttonPopularMoviesTryAgain
                                .setOnClickListener {
                                    popularMoviesAdapter.refresh()
                                }
                            FLIPPER_CHILD_POPULAR_MOVIES_ERROR_STATE
                        }
                    }
            }
        }
    }



    companion object {
        const val FLIPPER_CHILD_POPULAR_MOVIES_LOADING_STATE = 0
        const val FLIPPER_CHILD_POPULAR_MOVIES = 1
        const val FLIPPER_CHILD_POPULAR_MOVIES_ERROR_STATE = 2
    }
}