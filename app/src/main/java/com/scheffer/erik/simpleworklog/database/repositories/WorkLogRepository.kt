package com.scheffer.erik.simpleworklog.database.repositories

import androidx.annotation.WorkerThread
import com.scheffer.erik.simpleworklog.database.daos.WorkLogDao
import com.scheffer.erik.simpleworklog.database.entities.WorkLog

class WorkLogRepository(private val workLogDao: WorkLogDao) {
    fun getAll() = workLogDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(workLog: WorkLog) = workLogDao.insert(workLog)
}