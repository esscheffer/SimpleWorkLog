package com.scheffer.erik.simpleworklog.database.converters

import androidx.room.TypeConverter
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class OffsetDateTimeConverter {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    fun toOffsetDateTime(value: String?) =
            value?.let { formatter.parse(value, OffsetDateTime::from) }

    @TypeConverter
    fun fromOffsetDateTime(date: OffsetDateTime?) = date?.format(formatter)
}