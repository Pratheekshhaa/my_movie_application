package com.example.my_movie_application.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") key: String): MovieResponse
}
