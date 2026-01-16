package com.example.my_movie_application

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_movie_application.api.Movie
import com.example.my_movie_application.api.RetrofitClient
import kotlinx.coroutines.launch

class MoviesFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var trendingRecycler: RecyclerView
    private lateinit var popularRecycler: RecyclerView
    private lateinit var latestRecycler: RecyclerView
    private lateinit var topRatedRecycler: RecyclerView

    private val apiKey = BuildConfig.TMDB_API_KEY

    companion object {

        private const val ARG_SEARCH = "search"

        fun newInstance(query: String?): MoviesFragment {
            val fragment = MoviesFragment()
            val bundle = Bundle()
            bundle.putString(ARG_SEARCH, query)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // MATCHING YOUR NEW XML IDS
        progressBar = view.findViewById(R.id.progress_bar)
        trendingRecycler = view.findViewById(R.id.trending_recycler)
        popularRecycler = view.findViewById(R.id.popular_recycler)
        latestRecycler = view.findViewById(R.id.latest_recycler)
        topRatedRecycler = view.findViewById(R.id.top_rated_recycler)

        setupRecycler(trendingRecycler)
        setupRecycler(popularRecycler)
        setupRecycler(latestRecycler)
        setupRecycler(topRatedRecycler)

        loadMovies()
    }

    private fun setupRecycler(recyclerView: RecyclerView) {

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView.setHasFixedSize(true)
    }

    private fun loadMovies() {

        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {

            try {

                val api = RetrofitClient.apiService

                val trending = api.getTrendingMovies(apiKey)
                val popular = api.getPopularMovies(apiKey)
                val latest = api.getNowPlayingMovies(apiKey)
                val topRated = api.getTopRatedMovies(apiKey)

                trendingRecycler.adapter =
                    MovieAdapter(trending.results) { movie ->
                        openDetails(movie)
                    }

                popularRecycler.adapter =
                    MovieAdapter(popular.results) { movie ->
                        openDetails(movie)
                    }

                latestRecycler.adapter =
                    MovieAdapter(latest.results) { movie ->
                        openDetails(movie)
                    }

                topRatedRecycler.adapter =
                    MovieAdapter(topRated.results) { movie ->
                        openDetails(movie)
                    }

                progressBar.visibility = View.GONE

            } catch (e: Exception) {

                progressBar.visibility = View.GONE

                Toast.makeText(
                    requireContext(),
                    "Failed to load movies",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openDetails(movie: Movie) {

        val fragment = MovieDetailsFragment()

        val bundle = Bundle()
        bundle.putSerializable("movie", movie)

        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }
    fun searchMovies(query: String) {

        lifecycleScope.launch {

            try {

                val response = RetrofitClient.apiService
                    .searchMovies(BuildConfig.TMDB_API_KEY, query)

                trendingRecycler.adapter =
                    MovieAdapter(response.results) { movie ->
                        openDetails(movie)
                    }

            } catch (e: Exception) {

                Toast.makeText(requireContext(),
                    "Search failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
