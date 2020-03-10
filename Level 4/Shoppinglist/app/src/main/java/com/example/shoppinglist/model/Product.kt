package com.example.shoppinglist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "productTable")
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name:String,
    val amount:Int
)