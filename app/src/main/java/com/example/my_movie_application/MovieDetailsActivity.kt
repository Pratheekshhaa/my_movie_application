package com.example.my_movie_application

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.my_movie_application.api.Movie

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var bannerImage: ImageView
    private lateinit var titleText: TextView
    private lateinit var releaseDateText: TextView
    private lateinit var overviewText: TextView
    private lateinit var ratingProgress: ProgressBar
    private lateinit var playButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // IMPORTANT: Using YOUR existing XML
        setContentView(R.layout.fragment_movie_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bindViews()
        loadMovie()
    }

    private fun bindViews() {

        // MATCH XML IDs EXACTLY
        bannerImage = findViewById(R.id.banner)
        titleText = findViewById(R.id.title)
        releaseDateText = findViewById(R.id.releaseDate)
        overviewText = findViewById(R.id.overview)
        ratingProgress = findViewById(R.id.ratingBar)
        playButton = findViewById(R.id.playBtn)

        playButton.setOnClickListener {

            Toast.makeText(
                this,
                "Movie is Playing",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun loadMovie() {

        val movie = intent.getSerializableExtra("movie") as? Movie

        if (movie == null) {
            Toast.makeText(this, "Movie data not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Title
        titleText.text = movie.title
        supportActionBar?.title = movie.title

        // Overview
        overviewText.text = movie.overview

        // Banner Image
        val imageUrl =
            "https://image.tmdb.org/t/p/w780${movie.posterPath}"

        Glide.with(this)
            .load(imageUrl)
            .into(bannerImage)

        // TEMP: Disable rating until model supports it
        ratingProgress.progress = 0
    }
}
