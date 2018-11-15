package com.scheffer.erik.simpleworklog.database.repositories

import androidx.annotation.WorkerThread
import com.scheffer.erik.simpleworklog.database.daos.WorkShiftDao
import com.scheffer.erik.simpleworklog.database.entities.WorkShift

class WorkShiftRepository(private val workShiftDao: WorkShiftDao) {
    fun getAll() = workShiftDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insert(entity: WorkShift) = workShiftDao.insert(entity)
}