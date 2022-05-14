package com.rodrigoads.mymovies.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.popularMoviesFragment,
                R.id.searchMovieFragment,
                R.id.watchLaterFragment,
                R.id.categoriesFragment
            )
        )

        setSupportActionBar(mainActivityBinding.toolbar)
        mainActivityBinding.bottomNav.setupWithNavController(navController)
        mainActivityBinding.toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val topLevelDestinations =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!topLevelDestinations) {
                if (destination.id == R.id.movieDetailsFragment) {
                    mainActivityBinding.toolbar.setNavigationIcon(R.drawable.ic_back)
                } else {
                    mainActivityBinding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
                }
            }
            mainActivityBinding.toolbar.isVisible = destination.id != R.id.popularMoviesFragment

        }

        supportActionBar?.title = navController.currentDestination?.label
    }

}

