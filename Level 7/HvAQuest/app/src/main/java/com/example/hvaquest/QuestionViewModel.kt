package com.example.hvaquest

import androidx.lifecycle.ViewModel

class QuestionViewModel : ViewModel() {

    val questionRepository = QuestionRepository()
    val questions = questionRepository.getQuestions()

    fun getQuestion(n : Int): Question{
        return questions[n]
    }

    fun isValidIndex(n : Int): Boolean{
        return questions.size-1 >= n
    }
}
