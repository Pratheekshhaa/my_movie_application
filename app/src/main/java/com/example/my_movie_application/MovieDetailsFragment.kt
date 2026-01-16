package com.example.my_movie_application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.my_movie_application.api.Movie

class MovieDetailsFragment : Fragment() {

    private lateinit var banner: ImageView
    private lateinit var title: TextView
    private lateinit var overview: TextView
    private lateinit var releaseDate: TextView
    private lateinit var ratingProgress: ProgressBar
    private lateinit var playBtn: Button
    private lateinit var favoriteBtn: ImageButton
    private lateinit var watchlistBtn: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view =
            inflater.inflate(R.layout.fragment_movie_details, container, false)

        banner = view.findViewById(R.id.movie_banner)
        title = view.findViewById(R.id.movie_title)
        overview = view.findViewById(R.id.movie_overview)
        releaseDate = view.findViewById(R.id.release_date)
        ratingProgress = view.findViewById(R.id.rating_progress)
        playBtn = view.findViewById(R.id.btn_play)
        favoriteBtn = view.findViewById(R.id.btn_favorite)
        watchlistBtn = view.findViewById(R.id.btn_watchlist)

        val movie =
            arguments?.getSerializable("movie") as Movie

        bindMovie(movie)

        return view
    }

    private fun bindMovie(movie: Movie) {

        title.text = movie.title
        overview.text = movie.overview
        releaseDate.text = "Release: ${movie.releaseDate}"

        ratingProgress.progress =
            (movie.voteAverage * 10).toInt()

        val imageUrl =
            "https://image.tmdb.org/t/p/w780${movie.backdropPath}"

        Glide.with(this)
            .load(imageUrl)
            .into(banner)

        playBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Movie is Playing",
                Toast.LENGTH_SHORT
            ).show()
        }

        favoriteBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Added to Favorites",
                Toast.LENGTH_SHORT
            ).show()
        }

        watchlistBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Added to Watchlist",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
