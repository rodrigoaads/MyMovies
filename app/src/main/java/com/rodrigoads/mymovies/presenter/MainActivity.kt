package com.rodrigoads.mymovies.presenter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
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
            val topLevelDestinations = appBarConfiguration.topLevelDestinations.contains(destination.id)
            if(!topLevelDestinations){
                if (destination.id == R.id.movieDetailsFragment){
                    mainActivityBinding.toolbar.setNavigationIcon(R.drawable.ic_back)
                }else {
                    mainActivityBinding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
                }
            }
            mainActivityBinding.toolbar.isVisible = destination.id != R.id.popularMoviesFragment

        }

        /*mainActivityBinding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.popular -> {
                    navController.navigate(R.id.popular)
                    navController.clearBackStack(R.id.movieDetailsFragment)
                    true
                }
                R.id.search -> {
                    navController.navigate(R.id.search)
                    navController.clearBackStack(R.id.movieDetailsFragment)
                    true
                }
                R.id.watch_later -> {
                    navController.navigate(R.id.watch_later)
                    navController.clearBackStack(R.id.movieDetailsFragment)
                    true
                }
                R.id.categories -> {
                    navController.navigate(R.id.categories)
                    navController.clearBackStack(R.id.categoriesFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }*/
    }

}

