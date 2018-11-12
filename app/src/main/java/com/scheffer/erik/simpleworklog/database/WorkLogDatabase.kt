package com.scheffer.erik.simpleworklog.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.scheffer.erik.simpleworklog.database.converters.CalendarConverter
import com.scheffer.erik.simpleworklog.database.converters.RegisterTypeConverter
import com.scheffer.erik.simpleworklog.database.daos.WorkLogDao
import com.scheffer.erik.simpleworklog.database.daos.WorkShiftDao
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.database.entities.WorkShift

const val DATABASE_NAME = "worklog-db"

@Database(entities = [WorkLog::class, WorkShift::class], version = 1)
@TypeConverters(CalendarConverter::class, RegisterTypeConverter::class)
abstract class WorkLogDatabase : RoomDatabase() {
    abstract fun workLogDao(): WorkLogDao
    abstract fun workShiftDao(): WorkShiftDao
}