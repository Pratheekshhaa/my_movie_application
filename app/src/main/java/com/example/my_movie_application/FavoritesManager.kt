package com.example.my_movie_application

object FavoritesManager {

    private val favorites = mutableSetOf<Int>()

    fun add(id: Int) {
        favorites.add(id)
    }

    fun getAll(): List<Int> {
        return favorites.toList()
    }
}
