package com.example.rockpaperscissors.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rockpaperscissors.model.PastGame

@Database(entities = [PastGame::class], version = 1, exportSchema = false)
@TypeConverters(PastGameTypeConverters::class)
abstract class PastGameRoomDatabase : RoomDatabase() {

    abstract fun pastGameDao(): PastGameDao

    companion object {
        private const val DATABASE_NAME = "PAST_GAME_DATABASE"

        @Volatile
        private var shoppingListRoomDatabaseInstance: PastGameRoomDatabase? = null

        fun getDatabase(context: Context): PastGameRoomDatabase? {
            if (shoppingListRoomDatabaseInstance == null) {
                synchronized(PastGameRoomDatabase::class.java) {
                    if (shoppingListRoomDatabaseInstance == null) {
                        shoppingListRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,
                                PastGameRoomDatabase::class.java,
                                DATABASE_NAME
                            ).build()
                    }
                }
            }
            return shoppingListRoomDatabaseInstance
        }
    }

}