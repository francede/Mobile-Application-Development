package com.example.logicaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSubmit.setOnClickListener{checkAnswers()}

    }

    private fun checkAnswers(){
        var correctAnswers:Int = 0
        if(answer0.selectedItem as String == getString(R.string.t)) correctAnswers++
        if(answer1.selectedItem as String == getString(R.string.f)) correctAnswers++
        if(answer2.selectedItem as String == getString(R.string.f)) correctAnswers++
        if(answer3.selectedItem as String == getString(R.string.f)) correctAnswers++

        Toast.makeText(this, String.format(getString(R.string.correctAnswers), correctAnswers), Toast.LENGTH_LONG).show()
    }
}
