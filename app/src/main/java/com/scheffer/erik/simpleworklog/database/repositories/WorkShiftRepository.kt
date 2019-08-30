package com.scheffer.erik.simpleworklog.database.repositories

import androidx.annotation.WorkerThread
import com.scheffer.erik.simpleworklog.database.daos.WorkShiftDao
import com.scheffer.erik.simpleworklog.database.entities.WorkShift

class WorkShiftRepository(private val workShiftDao: WorkShiftDao) {
    fun getAll() = workShiftDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(entity: WorkShift) = workShiftDao.insert(entity)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun persist(workShift: WorkShift) {
        if (workShift.id == null) {
            workShiftDao.insert(workShift)
        } else {
            workShiftDao.update(workShift)
        }
    }

    fun getById(id: Long) = workShiftDao.findById(id)

    fun getOpenWorkShift() = workShiftDao.findOpenWorkShift()
}