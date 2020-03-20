package com.example.rockpaperscissors.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.rockpaperscissors.model.PastGame
import com.example.rockpaperscissors.model.Winner

@Dao
interface PastGameDao {
    @Query("SELECT * FROM PastGame")
    suspend fun getAllPastGames(): List<PastGame>

    @Insert
    suspend fun insertPastGame(reminder: PastGame)

    @Query("DELETE FROM pastgame")
    suspend fun deleteAllPastGames()

    @Query("SELECT COUNT(id) FROM PastGame WHERE winner=:winner")
    suspend fun getCountByWinner(winner: Winner): Int
}