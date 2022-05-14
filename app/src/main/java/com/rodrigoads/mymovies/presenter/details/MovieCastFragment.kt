package com.rodrigoads.mymovies.presenter.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodrigoads.mymovies.databinding.FragmentMovieCastBinding
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieCastFragment : Fragment() {
    private lateinit var movieCastBinding: FragmentMovieCastBinding
    private val movieCreditsViewModel: MovieCreditsViewModel by viewModels()
    private lateinit var movieCastAdapter: MovieCastAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieCastBinding = FragmentMovieCastBinding.inflate(inflater, container, false)
        return movieCastBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        movieCreditsViewModel.movieCredits.observe(viewLifecycleOwner, Observer {
            movieCastBinding.viewFlipperMovieCast.displayedChild = when (it) {
                is ResultUiState.Loading -> {
                    FLIPPER_CHILD_MOVIE_CAST_LOADING_STATE
                }
                is ResultUiState.Success -> {
                    movieCastAdapter.submitList(it.data.cast)
                    FLIPPER_CHILD_MOVIE_CAST
                }
                is ResultUiState.Error -> {
                    movieCastBinding.includeViewMoviesErrorState.buttonPopularMoviesTryAgain.setOnClickListener {
                        val parent = requireParentFragment().arguments
                        parent?.getInt("id")?.also { id ->
                            movieCreditsViewModel.getMovieCredits(id)
                        }
                    }
                    FLIPPER_CHILD_MOVIE_CAST_ERROR_STATE
                }
            }
        })
    }

    private fun initAdapter() {
        movieCastAdapter = MovieCastAdapter()
        with(movieCastBinding.recyclerViewMovieCast) {
            adapter = movieCastAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onStart() {
        super.onStart()
        if (movieCreditsViewModel.movieCredits.value == null) {
            val parent = requireParentFragment().arguments
            parent?.getInt("id")?.also {
                movieCreditsViewModel.getMovieCredits(it)
            }
        }
    }

    companion object {
        const val FLIPPER_CHILD_MOVIE_CAST_LOADING_STATE = 0
        const val FLIPPER_CHILD_MOVIE_CAST = 1
        const val FLIPPER_CHILD_MOVIE_CAST_ERROR_STATE = 2
    }
}