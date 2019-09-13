package com.scheffer.erik.simpleworklog.database.converters

import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng

class LatLngConverter {
    companion object {
        private const val LATITUDE_LONGITUDE_SEPARATOR = "|"
    }

    @TypeConverter
    fun stringToLatLng(value: String?) = value?.let {
        LatLng(it.split(LATITUDE_LONGITUDE_SEPARATOR)[0].toDouble(),
                it.split(LATITUDE_LONGITUDE_SEPARATOR)[1].toDouble())
    }

    @TypeConverter
    fun latLngToString(latLng: LatLng?): String? = if (latLng == null) {
        null
    } else {
        "${latLng.latitude}$LATITUDE_LONGITUDE_SEPARATOR${latLng.longitude}"
    }
}