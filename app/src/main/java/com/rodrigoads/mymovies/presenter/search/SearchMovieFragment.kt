package com.rodrigoads.mymovies.presenter.search

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.FragmentSearchMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMovieFragment : Fragment() {
    private lateinit var searchMovieBinding: FragmentSearchMovieBinding
    private val searchMovieViewModel: SearchMovieViewModel by activityViewModels()
    private lateinit var searchMovieAdapter: SearchMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchMovieBinding = FragmentSearchMovieBinding.inflate(layoutInflater, container, false)
        return searchMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchAdapter()
        observerInitialLoadState()

        searchMovieViewModel.searchMovie.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                searchMovieAdapter.submitData(it)
            }
        })
    }

    private fun observerInitialLoadState() {
        lifecycleScope.launch {
            searchMovieAdapter.loadStateFlow.collect { loadState ->
                searchMovieBinding.viewFlipperSearchMovie.displayedChild =
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            FLIPPER_CHILD_SEARCH_MOVIE_LOADING_STATE
                        }
                        is LoadState.NotLoading -> {
                            FLIPPER_CHILD_SEARCH_MOVIE
                        }
                        is LoadState.Error -> {
                            searchMovieBinding
                                .includeViewMoviesErrorState
                                .buttonPopularMoviesTryAgain
                                .setOnClickListener {
                                    searchMovieAdapter.refresh()
                                }
                            FLIPPER_CHILD_SEARCH_MOVIE_ERROR_STATE
                        }
                    }
            }
        }
    }

    private fun initSearchAdapter() {
        searchMovieAdapter = SearchMovieAdapter {
            val action = SearchMovieFragmentDirections
                .actionSearchMovieFragmentToMovieDetailsFragment(id = it.id)
            findNavController().navigate(action)
        }
        searchMovieBinding.recyclerViewSearchResult.apply {
            adapter = searchMovieAdapter.withLoadStateFooter(
                SearchMoviesLoadStateAdapter {
                    searchMovieAdapter.retry()
                })
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

        val searchView = menu.findItem(R.id.searchMenu).actionView as SearchView
        searchView.queryHint = context?.getString(R.string.search)

        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    p0?.let {
                        searchMovieBinding.recyclerViewSearchResult.scrollToPosition(0)
                        searchMovieViewModel.getMoviesBySearch(it)
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    p0.let {
                        if (it.isNullOrEmpty()) {
                            searchMovieBinding.recyclerViewSearchResult.scrollToPosition(0)
                        }
                    }
                    return true
                }

            })
        }
    }

    companion object {
        const val FLIPPER_CHILD_SEARCH_MOVIE_LOADING_STATE = 1
        const val FLIPPER_CHILD_SEARCH_MOVIE = 2
        const val FLIPPER_CHILD_SEARCH_MOVIE_ERROR_STATE = 3
    }

}