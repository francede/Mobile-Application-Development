package com.example.popularmovies

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviePage(
    @SerializedName("results") val movies: List<Movie>
): Parcelable