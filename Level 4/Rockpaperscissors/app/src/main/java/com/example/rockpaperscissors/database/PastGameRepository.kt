package com.example.rockpaperscissors.database

import android.content.Context
import com.example.rockpaperscissors.model.PastGame
import com.example.rockpaperscissors.model.Winner

class PastGameRepository(context: Context) {

    private val pastGameDao: PastGameDao

    init {
        val database = PastGameRoomDatabase.getDatabase(context)
        pastGameDao = database!!.pastGameDao()
    }

    suspend fun getAllPastGames(): List<PastGame> { return pastGameDao.getAllPastGames() }

    suspend fun insertPastGame(product: PastGame) { pastGameDao.insertPastGame(product) }

    suspend fun deleteAllPastGames() { pastGameDao.deleteAllPastGames() }

    suspend fun getCountByWinner(winner: Winner): Int { return pastGameDao.getCountByWinner(winner) }

}