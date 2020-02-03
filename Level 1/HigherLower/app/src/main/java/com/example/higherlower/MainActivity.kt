package com.example.higherlower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentThrow:Int = 1
    private var lastThrow:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHigher.setOnClickListener{onHigherClick()}
        btnEquals.setOnClickListener{onEqualsClick()}
        btnLower.setOnClickListener{onLowerClick()}

        updateUI()
    }

    private fun updateUI(){
        tvLastThrow.text = getString(R.string.last_throw, lastThrow)
        var r:Int=1
        when(currentThrow){
            1-> r = R.drawable.dice1
            2-> r = R.drawable.dice2
            3-> r = R.drawable.dice3
            4-> r = R.drawable.dice4
            5-> r = R.drawable.dice5
            6-> r = R.drawable.dice6
        }
        ivDice.setImageResource(r)
    }

    private fun rollDice(){
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    private fun onHigherClick(){
        rollDice()
        if(currentThrow > lastThrow) onCorrect()
        else onIncorrect()
    }

    private fun onEqualsClick(){
        rollDice()
        if(currentThrow == lastThrow) onCorrect()
        else onIncorrect()
    }

    private fun onLowerClick(){
        rollDice()
        if(currentThrow < lastThrow) onCorrect()
        else onIncorrect()
    }

    private fun onCorrect(){
        Toast.makeText(this, R.string.correct, Toast.LENGTH_LONG).show()
    }

    private fun onIncorrect(){
        Toast.makeText(this, R.string.incorrect, Toast.LENGTH_LONG).show()
    }

}
