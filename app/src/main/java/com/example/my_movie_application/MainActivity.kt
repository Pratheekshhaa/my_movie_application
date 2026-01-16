package com.example.my_movie_application

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.my_movie_application.api.RetrofitClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var searchView: SearchView
    private lateinit var backdropImage: ImageView   // ✅ CORRECT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottom_navigation)
        searchView = findViewById(R.id.search_view)
        backdropImage = findViewById(R.id.backdropImage) // ✅ matches XML id

        loadHeroBanner()

        if (savedInstanceState == null) {
            loadFragment(MoviesFragment())
        }

        bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.movies -> {
                    loadFragment(MoviesFragment())
                    true
                }

                R.id.favorites -> {
                    loadFragment(FavoritesFragment())
                    true
                }

                R.id.watchlist -> {
                    loadFragment(WatchlistFragment())
                    true
                }

                else -> false
            }
        }

        setupSearch()
    }

    private fun loadHeroBanner() {

        MainScope().launch {

            try {

                val response =
                    RetrofitClient.apiService.getTrendingMovies(BuildConfig.TMDB_API_KEY)

                val bannerPath = response.results[0].backdropPath   // ✅ correct field

                val bannerUrl =
                    "https://image.tmdb.org/t/p/w1280$bannerPath"

                Glide.with(this@MainActivity)
                    .load(bannerUrl)        // ✅ correct variable
                    .into(backdropImage)

            } catch (_: Exception) {
                // silent fail
            }
        }
    }

    private fun setupSearch() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                val fragment =
                    supportFragmentManager.findFragmentById(R.id.fragment_container)

                if (fragment is MoviesFragment && !query.isNullOrEmpty()) {
                    fragment.searchMovies(query)
                }

                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun loadFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
