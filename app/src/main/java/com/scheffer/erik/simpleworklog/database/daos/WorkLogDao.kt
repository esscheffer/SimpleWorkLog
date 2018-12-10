package com.scheffer.erik.simpleworklog.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.scheffer.erik.simpleworklog.database.entities.WorkLog

@Dao
interface WorkLogDao {
    @Query("SELECT * FROM WorkLog ORDER BY registerTime DESC")
    fun getAll(): LiveData<List<WorkLog>>

    @Insert
    fun insert(workLog: WorkLog): Long

    @Query("DELETE FROM WorkLog")
    fun deleteAll()

    @Delete
    fun delete(workLog: WorkLog)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(workLog: WorkLog)

    @Query("SELECT * FROM WorkLog WHERE id = :id")
    fun findById(id: Long): LiveData<WorkLog?>
}