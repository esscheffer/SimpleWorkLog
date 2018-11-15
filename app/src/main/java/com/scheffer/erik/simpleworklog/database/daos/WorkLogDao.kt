package com.scheffer.erik.simpleworklog.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.scheffer.erik.simpleworklog.database.entities.WorkLog

@Dao
interface WorkLogDao {
    @Query("SELECT * from WorkLog")
    fun getAll(): LiveData<List<WorkLog>>

    @Insert
    fun insert(workLog: WorkLog)

    @Query("DELETE from WorkLog")
    fun deleteAll()
}