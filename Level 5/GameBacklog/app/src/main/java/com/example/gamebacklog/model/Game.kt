package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
class Game (

    var name : String,
    var platform : String,
    var releaseDate : Date,

    @PrimaryKey(autoGenerate = true)
    var id : Long? = null
) : Parcelable