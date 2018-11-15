package com.scheffer.erik.simpleworklog.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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