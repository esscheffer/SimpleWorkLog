package com.scheffer.erik.simpleworklog.database.converters

import androidx.room.TypeConverter
import com.scheffer.erik.simpleworklog.RegisterType

class RegisterTypeConverter {
    @TypeConverter
    fun stringToRegisterType(value: String?) = value?.let { RegisterType.valueOf(it) }

    @TypeConverter
    fun registerTypeToString(type: RegisterType?) = type?.name
}