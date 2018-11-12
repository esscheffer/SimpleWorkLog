package com.scheffer.erik.simpleworklog.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.scheffer.erik.simpleworklog.database.entities.WorkShift

@Dao
interface WorkShiftDao {
    @Query("SELECT * from WorkShift")
    fun getAll(): LiveData<List<WorkShift>>

    @Insert
    fun insert(workShift: WorkShift)

    @Query("DELETE from WorkShift")
    fun deleteAll()
}