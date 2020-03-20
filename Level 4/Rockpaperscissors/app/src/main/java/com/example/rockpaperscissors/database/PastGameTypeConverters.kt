package com.example.rockpaperscissors.database

import androidx.room.TypeConverter
import com.example.rockpaperscissors.model.Choice
import com.example.rockpaperscissors.model.Winner

class PastGameTypeConverters {
    @TypeConverter
    fun fromChoice(choice: Choice): Int{
        return choice.ordinal
    }

    @TypeConverter
    fun intToChoice(int: Int): Choice{
        return Choice.values()[int]
    }

    @TypeConverter
    fun fromWinner(winner: Winner): Int{
        return winner.ordinal
    }

    @TypeConverter
    fun intToWinner(int: Int): Winner{
        return Winner.values()[int]
    }
}