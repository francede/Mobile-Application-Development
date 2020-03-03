package com.example.swipequiz

data class Question(
    val questionText : String,
    val answer : Boolean
){
    companion object{
        val QUESTION_TEXT = arrayOf(
            "1 means true",
            "The sun is a planet",
            "0 means false",
            "This is a statement",
            "Water is a gas at NTP",
            "Programming apps with Kotlin is fun"
        )

        val QUESTION_ANSWER = arrayOf(
            true,
            false,
            true,
            true,
            false,
            true
        )
    }
}