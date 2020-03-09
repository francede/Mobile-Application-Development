package com.example.studentportal

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal (
    var name: String,
    var uri: Uri
) : Parcelable