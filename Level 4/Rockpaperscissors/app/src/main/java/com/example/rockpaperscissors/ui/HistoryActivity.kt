package com.example.rockpaperscissors.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.PastGameRepository
import com.example.rockpaperscissors.model.PastGame
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var pastGameRepository: PastGameRepository

    private val pastGames = arrayListOf<PastGame>()
    private val pastGameAdapter = PastGameAdapter(pastGames,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        pastGameRepository = PastGameRepository(this)

        initViews()
    }

    private fun initViews(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.history)
        rvHistory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvHistory.adapter = pastGameAdapter
        getGameHistory()
    }

    private fun getGameHistory(){
        mainScope.launch {
            pastGames.clear()
            withContext(Dispatchers.IO){
                pastGames.addAll(pastGameRepository.getAllPastGames().reversed())
            }
            pastGameAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_delete -> {
                mainScope.launch {
                    withContext(Dispatchers.IO){
                        pastGameRepository.deleteAllPastGames()
                    }
                    getGameHistory()
                }
                true
            }

            R.id.action_sort -> {
                pastGames.reverse()
                pastGameAdapter.notifyDataSetChanged()
                true
            }

            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
