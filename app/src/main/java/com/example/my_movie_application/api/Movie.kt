package com.example.my_movie_application.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(

    val id: Int,
    val title: String,
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int

) : Serializable
