package com.scheffer.erik.simpleworklog.database.converters

import androidx.room.TypeConverter
import java.util.*

class CalendarConverter {
    @TypeConverter
    fun fromTimestamp(value: Long) = Calendar.getInstance().apply { timeInMillis = value }

    @TypeConverter
    fun calendarToTimestamp(date: Calendar) = date.timeInMillis
}