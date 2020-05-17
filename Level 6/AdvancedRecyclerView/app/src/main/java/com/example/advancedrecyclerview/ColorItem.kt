package com.example.advancedrecyclerview

data class ColorItem(
    var hex: String,
    var name: String
) {
    fun getImageUrl() : String { return "http://singlecolorimage.com/get/$hex/1080x1080" }
}
