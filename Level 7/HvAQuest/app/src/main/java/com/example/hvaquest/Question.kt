package com.example.hvaquest

data class Question(
    var question: String,
    var choices: Array<String>,
    var correctAnswer: String,
    var clue: Int
)
