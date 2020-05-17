package com.example.numbertrivia

import retrofit2.Call

class NumbersRepository {
    private val numbersApi: NumbersApiService = NumbersApi.createApi()

    fun getRandomNumberTrivia() : Call<Trivia> { return numbersApi.getRandomNumberTrivia() }
}
