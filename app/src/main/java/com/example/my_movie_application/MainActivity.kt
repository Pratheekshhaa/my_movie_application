package com.example.my_movie_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        loadFragment(MoviesFragment())

        val bottom = findViewById<BottomNavigationView>(R.id.bottomNav)

        bottom.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.movies -> loadFragment(MoviesFragment())
                R.id.fav -> loadFragment(FavoritesFragment())
                R.id.watch -> loadFragment(WatchlistFragment())

            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
