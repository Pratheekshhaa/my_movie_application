package com.example.my_movie_application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.my_movie_application.api.Movie

class MovieAdapter(
    private val list: List<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val poster: ImageView = itemView.findViewById(R.id.moviePoster)
        val title: TextView = itemView.findViewById(R.id.movieTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = list[position]

        holder.title.text = movie.title

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.poster)

        // CLICK HANDLER
        holder.itemView.setOnClickListener {
            onClick(movie)
        }
    }

    override fun getItemCount(): Int = list.size
}
