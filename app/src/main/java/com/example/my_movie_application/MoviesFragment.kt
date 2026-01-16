package com.example.my_movie_application

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_movie_application.api.MovieResponse
import com.example.my_movie_application.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import com.example.my_movie_application.BuildConfig
import retrofit2.Response

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        RetrofitClient.api.getMovies(BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {

                    if (response.isSuccessful) {
                        val movies = response.body()?.results ?: emptyList()
                        recycler.adapter = MovieAdapter(movies)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}
