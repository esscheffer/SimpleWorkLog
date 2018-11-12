package com.scheffer.erik.simpleworklog.database.converters

import android.arch.persistence.room.TypeConverter
import com.scheffer.erik.simpleworklog.RegisterType

class RegisterTypeConverter {
    @TypeConverter
    fun fromString(value: String) = RegisterType.valueOf(value)

    @TypeConverter
    fun calendarToTimestamp(type: RegisterType) = type.name
}