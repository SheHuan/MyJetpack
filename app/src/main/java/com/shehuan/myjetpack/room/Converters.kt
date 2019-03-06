package com.shehuan.myjetpack.room

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun timestampToDate(timestamp: Long?): Date? {
        return timestamp?.let {
            Date(timestamp)
        }
    }
}