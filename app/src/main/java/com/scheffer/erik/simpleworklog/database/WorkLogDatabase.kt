package com.scheffer.erik.simpleworklog.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.scheffer.erik.simpleworklog.database.converters.LatLngConverter
import com.scheffer.erik.simpleworklog.database.converters.OffsetDateTimeConverter
import com.scheffer.erik.simpleworklog.database.converters.RegisterTypeConverter
import com.scheffer.erik.simpleworklog.database.daos.GeofenceDao
import com.scheffer.erik.simpleworklog.database.daos.WorkLogDao
import com.scheffer.erik.simpleworklog.database.daos.WorkShiftDao
import com.scheffer.erik.simpleworklog.database.entities.GeofenceEntity
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.database.entities.WorkShift

const val DATABASE_NAME = "worklog-db"

@Database(entities = [WorkLog::class, WorkShift::class, GeofenceEntity::class], version = 1)
@TypeConverters(OffsetDateTimeConverter::class, RegisterTypeConverter::class, LatLngConverter::class)
abstract class WorkLogDatabase : RoomDatabase() {
    abstract fun workLogDao(): WorkLogDao
    abstract fun workShiftDao(): WorkShiftDao
    abstract fun geofenceDao(): GeofenceDao
}