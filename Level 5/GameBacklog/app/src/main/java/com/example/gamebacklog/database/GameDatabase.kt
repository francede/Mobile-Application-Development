package com.example.gamebacklog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gamebacklog.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(GameTypeConverters::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDAO

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var databaseInstance: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase? {
            if (databaseInstance == null) {
                synchronized(GameDatabase::class.java) {
                    if (databaseInstance == null) {
                        databaseInstance =
                            Room.databaseBuilder(context.applicationContext,GameDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return databaseInstance
        }
    }
}
