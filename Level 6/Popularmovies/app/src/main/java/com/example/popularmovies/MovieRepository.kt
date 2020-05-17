package com.example.popularmovies

import retrofit2.Call

class MovieRepository {
    private val movieApi: MovieApiService = MovieApi.createApi()

    fun getMovies(key: String, year: String) : Call<MoviePage> {
        return movieApi.getMovies(key,year)
    }
}
