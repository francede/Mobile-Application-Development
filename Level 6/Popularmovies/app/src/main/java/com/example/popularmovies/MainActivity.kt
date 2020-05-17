package com.example.popularmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val apiKey = "e2a93a83e0fb33529fc81eec909d4912"
    }

    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies) { movie:Movie -> startMovieActivity(movie) }
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
    }

    private fun initViews(){
        rvMovies.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        rvMovies.adapter = movieAdapter

        btnSubmit.setOnClickListener{
            viewModel.getMovies(apiKey, etYear.text.toString())
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.movies.observe(this, Observer {
            movies.clear()
            movies.addAll(it.movies)
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.error.observe(this, Observer { Toast.makeText(this, it, Toast.LENGTH_LONG).show() })
    }

    private fun startMovieActivity(movie: Movie){
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(MovieActivity.MOVIE_EXTRA, movie)
        startActivity(intent)
    }

    private fun makeRequestString(apiKey:String, year:Int): String{
        return "https://api.themoviedb.org/3/discover/movie?" +
                apiKey +
                "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_year=" +
                year.toString()
    }
}
