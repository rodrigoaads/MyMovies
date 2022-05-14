package com.rodrigoads.mymovies.presenter.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.FragmentSimilarMoviesBinding
import com.rodrigoads.mymovies.presenter.popular.PopularMoviesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SimilarMoviesFragment : Fragment() {
    private lateinit var similarMoviesBinding: FragmentSimilarMoviesBinding
    private val similarMoviesViewModel: SimilarMoviesViewModel by viewModels()
    private lateinit var similarMoviesAdapter: SimilarMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        similarMoviesBinding = FragmentSimilarMoviesBinding.inflate(inflater, container, false)
        return similarMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSimilarAdapter()
        observeInitialLoadState()

        similarMoviesViewModel.similarMovies.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                similarMoviesAdapter.submitData(it)
            }
        })

    }

    override fun onStart() {
        super.onStart()
        if (similarMoviesViewModel.similarMovies.value == null) {
            val parent = requireParentFragment().arguments
            parent?.getInt("id")?.let {
                similarMoviesViewModel.getSimilarMovies(it)
            }
        }
    }

    private fun initSimilarAdapter() {
        similarMoviesAdapter = SimilarMoviesAdapter {
            val action = MovieDetailsFragmentDirections
                .actionMovieDetailsFragmentSelf(it.id)
            findNavController().navigate(action)
        }

        with(similarMoviesBinding.popularMoviesRecyclerView) {
            setHasFixedSize(true)
            adapter = similarMoviesAdapter.withLoadStateFooter(
                SimilarMoviesLoadStateAdapter {
                    similarMoviesAdapter.retry()
                }
            )
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            similarMoviesAdapter.loadStateFlow.collectLatest { loadState ->
                similarMoviesBinding.viewFlipperSimilarMovies.displayedChild =
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            FLIPPER_CHILD_SIMILAR_MOVIES_LOADING_STATE
                        }
                        is LoadState.NotLoading -> {
                            FLIPPER_CHILD_SIMILAR_MOVIES
                        }
                        is LoadState.Error -> {
                            FLIPPER_CHILD_SIMILAR_MOVIES_ERROR_STATE
                        }
                    }
            }
        }
    }

    companion object {
        const val FLIPPER_CHILD_SIMILAR_MOVIES_LOADING_STATE = 0
        const val FLIPPER_CHILD_SIMILAR_MOVIES = 1
        const val FLIPPER_CHILD_SIMILAR_MOVIES_ERROR_STATE = 2
    }
}