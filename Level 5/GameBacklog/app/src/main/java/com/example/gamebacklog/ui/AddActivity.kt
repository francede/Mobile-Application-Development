package com.example.gamebacklog.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import java.lang.Exception
import java.time.LocalDate
import java.util.*

class AddActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_GAME = "EXTRA_GAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener { onAddReminder() }
    }

    private fun onAddReminder() {
        val titleText = etTitle.text.toString()
        val platformText = etPlatform.text.toString()

        val y = etYear.text.toString().toInt()
        val m = etMonth.text.toString().toInt() - 1 //Calendar months are from 0 to 11
        val d = etDay.text.toString().toInt()

        val c = Calendar.getInstance()
        c.isLenient = false
        c.timeInMillis = 0
        c.set(y,m,d)

        when{
            titleText.isBlank() -> Toast.makeText(this, R.string.invalid_title, Toast.LENGTH_SHORT).show()
            platformText.isBlank() -> Toast.makeText(this, R.string.invalid_platform, Toast.LENGTH_SHORT).show()
            else ->
            try{
                val time = c.time
                val game = Game(titleText, platformText, time)
                val resIntent = Intent()
                resIntent.putExtra(EXTRA_GAME, game)
                setResult(Activity.RESULT_OK, resIntent)
                finish()
            }catch (e : Exception){
                Toast.makeText(this, R.string.invalid_date, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
