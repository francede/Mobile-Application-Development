package com.example.popularmovies

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("title" ) val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("overview") val overview: String,

    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("poster_path") val posterPath: String
) : Parcelable {
    fun getBackdropUrl(): String{
        return "https://image.tmdb.org/t/p/w500/$backdropPath"
    }
    fun getPosterUrl(): String{
        return "https://image.tmdb.org/t/p/w185/$posterPath"
    }
}