package com.example.popularmovies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface MovieApiService {

    // The GET method needed to retrieve a random number trivia.
    @GET("/3/discover/movie?" +
            "language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun getMovies(
        @Query("api_key") key:String,
        @Query("primary_release_year") year:String
    ): Call<MoviePage>
}
