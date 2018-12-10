package com.scheffer.erik.simpleworklog.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.scheffer.erik.simpleworklog.database.entities.WorkShift

@Dao
interface WorkShiftDao {
    @Query("SELECT * FROM WorkShift")
    fun getAll(): LiveData<List<WorkShift>>

    @Insert
    fun insert(workShift: WorkShift)

    @Query("DELETE FROM WorkShift")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(workShift: WorkShift)

    @Query("SELECT * FROM WorkShift WHERE id = :id")
    fun findById(id: Long): LiveData<WorkShift?>
}