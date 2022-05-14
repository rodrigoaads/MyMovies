package com.rodrigoads.mymovies.presenter.watchlater

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.FragmentWatchLaterBinding
import com.rodrigoads.mymovies.presenter.watchlater.model.WatchLaterUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchLaterFragment : Fragment() {
    private lateinit var watchLaterBinding: FragmentWatchLaterBinding
    private val watchLaterViewModel: WatchLaterViewModel by activityViewModels()
    private lateinit var watchLaterAdapter: WatchLaterAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        watchLaterBinding = FragmentWatchLaterBinding.inflate(layoutInflater, container, false)
        return watchLaterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWatchLaterAdapter()

        watchLaterViewModel.watchLaterList.observe(viewLifecycleOwner, Observer {
            initWatchLaterAdapter()
            watchLaterAdapter.submitList(it.sortedByDescending { watchLaterMovie ->
                watchLaterMovie.position
            })
        })
    }

    override fun onResume() {
        super.onResume()
        watchLaterBinding.recyclerViewMoviesByWatchLater.scrollToPosition(0)
    }

    private fun initWatchLaterAdapter() {
        watchLaterAdapter = WatchLaterAdapter(::getMovieDetails, ::removeWatchLaterMovie)

        watchLaterBinding.recyclerViewMoviesByWatchLater.apply {
            adapter = watchLaterAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getMovieDetails(id: Int) {
        val action = WatchLaterFragmentDirections
            .actionWatchLaterFragmentToMovieDetailsFragment(id = id)
        findNavController().navigate(action)
    }

    private fun removeWatchLaterMovie(id: Int) {
        lifecycleScope.launch {
            watchLaterViewModel.removeWatchLater(id)
        }
    }
}