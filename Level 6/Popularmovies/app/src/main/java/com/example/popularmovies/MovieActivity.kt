package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_movie.*

class MovieActivity : AppCompatActivity() {

    companion object{
        const val MOVIE_EXTRA = "MOVIE_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        initViews()
    }

    private fun initViews(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA)

        if(movie != null){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            tvRating.text = movie.rating.toString()
            tvReleaseDate.text = movie.releaseDate

            Glide.with(this).load(movie.getBackdropUrl()).into(ivBackdrop)
            Glide.with(this).load(movie.getPosterUrl()).into(ivPoster)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
