package com.scheffer.erik.simpleworklog.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
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