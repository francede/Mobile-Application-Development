package com.example.gamebacklog.database

import androidx.room.TypeConverter
import java.util.*

class GameTypeConverters {
    @TypeConverter
    fun dateToEpoch(date: Date?): Long? { return date?.time }

    @TypeConverter
    fun epochToDate(value: Long?): Date? { return value?.let { Date(it) } }
}