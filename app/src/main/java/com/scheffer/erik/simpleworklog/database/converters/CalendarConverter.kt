package com.scheffer.erik.simpleworklog.database.converters

import androidx.room.TypeConverter
import java.util.*

class CalendarConverter {
    @TypeConverter
    fun timestampToCalendar(value: Long?) =
            value?.let { Calendar.getInstance().apply { timeInMillis = it } }

    @TypeConverter
    fun calendarToTimestamp(date: Calendar?) = date?.timeInMillis
}