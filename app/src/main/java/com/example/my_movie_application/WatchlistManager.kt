package com.example.my_movie_application

object WatchlistManager {

    private val watchlist = mutableSetOf<Int>()

    fun add(id: Int) {
        watchlist.add(id)
    }

    fun getAll(): List<Int> {
        return watchlist.toList()
    }
}
