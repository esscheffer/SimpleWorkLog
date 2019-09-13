package com.scheffer.erik.simpleworklog.database.repositories

import androidx.annotation.WorkerThread
import com.scheffer.erik.simpleworklog.database.daos.WorkLogDao
import com.scheffer.erik.simpleworklog.database.entities.WorkLog

class WorkLogRepository(private val workLogDao: WorkLogDao) {
    fun getAll() = workLogDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun persist(workLog: WorkLog): Long {
        val workLogId = workLog.id
        return if (workLogId == null) {
            workLogDao.insert(workLog)
        } else {
            workLogDao.update(workLog)
            workLogId
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(workLog: WorkLog) = workLogDao.delete(workLog)

    fun getById(id: Long) = workLogDao.findById(id)
}