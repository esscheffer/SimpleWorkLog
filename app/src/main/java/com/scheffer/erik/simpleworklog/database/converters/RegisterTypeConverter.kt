package com.scheffer.erik.simpleworklog.database.converters

import androidx.room.TypeConverter
import com.scheffer.erik.simpleworklog.RegisterType

class RegisterTypeConverter {
    @TypeConverter
    fun fromString(value: String) = RegisterType.valueOf(value)

    @TypeConverter
    fun calendarToTimestamp(type: RegisterType) = type.name
}