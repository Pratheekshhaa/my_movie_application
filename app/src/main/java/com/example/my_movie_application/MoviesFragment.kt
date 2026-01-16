package com.example.my_movie_application

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_movie_application.api.Movie
import com.example.my_movie_application.api.RetrofitClient
import kotlinx.coroutines.launch

class MoviesFragment : Fragment() {

    private lateinit var trendingRecycler: RecyclerView
    private lateinit var popularRecycler: RecyclerView
    private lateinit var searchBox: EditText

    private var searchQuery: String? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchQuery = arguments?.getString(ARG_SEARCH)
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

        // MATCH YOUR XML IDS
        searchBox = view.findViewById(R.id.searchBox)
        trendingRecycler = view.findViewById(R.id.trendingRecycler)
        popularRecycler = view.findViewById(R.id.popularRecycler)

        setupRecycler(trendingRecycler)
        setupRecycler(popularRecycler)

        loadMovies()
    }

    private fun setupRecycler(recyclerView: RecyclerView) {

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView.setHasFixedSize(true)
    }

    private fun loadMovies() {

        lifecycleScope.launch {

            try {

                // USING YOUR OLD WORKING API STYLE
                val response =
                    RetrofitClient.api.getMovies(BuildConfig.TMDB_API_KEY)

                trendingRecycler.adapter =
                    MovieAdapter(response.results) { movie ->
                        openDetails(movie)
                    }

                popularRecycler.adapter =
                    MovieAdapter(response.results) { movie ->
                        openDetails(movie)
                    }

            } catch (e: Exception) {

                (activity as? MainActivity)?.showToast("Failed to load movies")
            }
        }
    }

    private fun openDetails(movie: Movie) {

        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)

        intent.putExtra("movie", movie)

        startActivity(intent)
    }
}
