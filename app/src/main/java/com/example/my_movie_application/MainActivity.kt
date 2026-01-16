package com.example.my_movie_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottom_navigation)
        searchView = findViewById(R.id.search_view)

        setupBottomNavigation()
        setupSearch()

        if (savedInstanceState == null) {
            loadFragment(MoviesFragment())
        }
    }

    private fun setupBottomNavigation() {

        bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.movies -> loadFragment(MoviesFragment())

                R.id.fav -> loadFragment(FavoritesFragment())

                R.id.watch -> loadFragment(WatchlistFragment())
            }
            true
        }
    }

    private fun setupSearch() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {
                    loadFragment(MoviesFragment.newInstance(it))
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText.isNullOrEmpty()) {
                    loadFragment(MoviesFragment())
                } else {
                    loadFragment(MoviesFragment.newInstance(newText))
                }
                return true
            }
        })
    }

    private fun loadFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
