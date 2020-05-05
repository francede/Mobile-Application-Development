package com.example.gamebacklog.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.Game

class GameRepository(context: Context) {

    private val gameDAO: GameDAO

    init {
        val database = GameDatabase.getDatabase(context)
        gameDAO = database!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDAO.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDAO.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDAO.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDAO.deleteAllGames()
    }

}
