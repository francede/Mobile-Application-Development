package com.example.popularmovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository()
    val movies = MutableLiveData<MoviePage>()
    val error = MutableLiveData<String>()

    fun getMovies(key: String, year: String) {
        movieRepository.getMovies(key, year).enqueue(object : Callback<MoviePage> {
            override fun onResponse(call: Call<MoviePage>, response: Response<MoviePage>) {
                if (response.isSuccessful) movies.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<MoviePage>, t: Throwable) {
                error.value = t.message
            }
        })
    }

}
