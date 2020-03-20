package com.example.rockpaperscissors.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.PastGameRepository
import com.example.rockpaperscissors.model.Choice
import com.example.rockpaperscissors.model.PastGame
import com.example.rockpaperscissors.model.Winner
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var pastGameRepository: PastGameRepository

    private val mainScope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pastGameRepository = PastGameRepository(this)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        updateWinnerHistory()
    }

    private fun initViews(){
        ivRock.setOnClickListener{ play(Choice.ROCK) }
        ivPaper.setOnClickListener{ play(Choice.PAPER) }
        ivScissors.setOnClickListener{ play(Choice.SCISSORS) }
        updateWinnerHistory()
    }

    private fun updateWinnerHistory(){
        mainScope.launch {
            var playerWins = 0
            var draws = 0
            var computerWins = 0
            withContext(Dispatchers.IO){
                playerWins = pastGameRepository.getCountByWinner(Winner.PLAYER)
                draws = pastGameRepository.getCountByWinner(Winner.DRAW)
                computerWins = pastGameRepository.getCountByWinner(Winner.COMPUTER)
            }
            tvStatistics.text = getString(R.string.statistics, playerWins.toString(), draws.toString(), computerWins.toString())
        }
    }

    private fun startHistoryActivity(){
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_history -> {
                startHistoryActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Game logic and ui updates
    private fun play(choice: Choice){
        val computerChoice = Choice.values()[Random.nextInt(3)]
        val winner: Winner

        if(choice==computerChoice){
            //Draw
            winner = Winner.DRAW
            tvWinner.text = getString(R.string.draw)
        }else if (
            (choice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
            (choice == Choice.PAPER && computerChoice == Choice.ROCK) ||
            (choice == Choice.SCISSORS && computerChoice == Choice.PAPER)) {
            //Player win
            winner = Winner.PLAYER
            tvWinner.text = getString(R.string.win)
        } else{
            //Computer win
            winner = Winner.COMPUTER
            tvWinner.text = getString(R.string.lose)
        }

        //set images
        when(choice){
            Choice.ROCK -> ivYou.setImageResource(R.drawable.rock)
            Choice.PAPER -> ivYou.setImageResource(R.drawable.paper)
            Choice.SCISSORS -> ivYou.setImageResource(R.drawable.scissors)
        }
        when(computerChoice){
            Choice.ROCK -> ivCom.setImageResource(R.drawable.rock)
            Choice.PAPER -> ivCom.setImageResource(R.drawable.paper)
            Choice.SCISSORS -> ivCom.setImageResource(R.drawable.scissors)
        }

        val game = PastGame(null, System.currentTimeMillis(), choice, computerChoice, winner)
        mainScope.launch {
            withContext(Dispatchers.IO){
                pastGameRepository.insertPastGame(game)
            }
            updateWinnerHistory()
        }
    }
}
