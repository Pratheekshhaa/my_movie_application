package com.example.my_movie_application

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_movie_application.api.RetrofitClient
import com.example.my_movie_application.api.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        lifecycleScope.launch {

            val response: Response<MovieResponse> = withContext(Dispatchers.IO) {
                RetrofitClient.api
                    .getMovies(BuildConfig.TMDB_API_KEY)
                    .execute()
            }

            if (response.isSuccessful) {

                val movies = response.body()?.results ?: emptyList()

                recycler.adapter = MovieAdapter(movies)
            }
        }
    }
}
