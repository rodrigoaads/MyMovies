package com.rodrigoads.mymovies.presenter.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.FragmentPopularMoviesBinding
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.details.MovieDetailsFragment
import com.rodrigoads.mymovies.presenter.popular.model.PopularMoviesUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.hypot

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
    private lateinit var popularMoviesBinding: FragmentPopularMoviesBinding
    private val popularMoviesViewModel: PopularMoviesViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        popularMoviesBinding =
            FragmentPopularMoviesBinding.inflate(layoutInflater, container, false)
        return popularMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularMoviesViewModel.firstPopularMovie.observe(viewLifecycleOwner, Observer {
            popularMoviesBinding.viewFlipperFirstPopularMovie.displayedChild = when (it) {
                is ResultUiState.Loading -> {
                    FLIPPER_CHILD_FIRST_POPULAR_MOVIE_LOADING_STATE
                }
                is ResultUiState.Success -> {
                    setFirstPopularMovie(it.data)
                    FLIPPER_CHILD_FIRST_POPULAR_MOVIE
                }
                is ResultUiState.Error -> {
                    FLIPPER_CHILD_FIRST_POPULAR_MOVIE_ERROR_STATE
                }
            }
        })
    }


    private fun setFirstPopularMovie(firstPopularMovie: PopularMoviesUiModel?) {
        val movieId = firstPopularMovie?.id ?: 0
        firstPopularMovie?.let {
            popularMoviesBinding.includeViewFirstPopularMovie.textViewFirstMovieTitle.text =
                it.title
        }

        Glide.with(this)
            .load(BuildConfig.GET_IMAGE_URL + firstPopularMovie?.backdrop_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .into(popularMoviesBinding.includeViewFirstPopularMovie.imageViewFirstPopularMovieBackdrop)

        popularMoviesBinding.includeViewFirstPopularMovie.textViewFirstMovieDetails.setOnClickListener {
            val action = PopularMoviesFragmentDirections
                .actionPopularMoviesFragmentToMovieDetailsFragment(id = movieId)
            findNavController().navigate(action)
        }
    }

    companion object {
        const val FLIPPER_CHILD_FIRST_POPULAR_MOVIE_LOADING_STATE = 0
        const val FLIPPER_CHILD_FIRST_POPULAR_MOVIE = 1
        const val FLIPPER_CHILD_FIRST_POPULAR_MOVIE_ERROR_STATE = 2
    }
}