package com.example.numbertrivia

import retrofit2.Call
import retrofit2.http.GET

public interface NumbersApiService {

    @GET("/random/trivia?json")
    fun getRandomNumberTrivia(): Call<Trivia>
}
