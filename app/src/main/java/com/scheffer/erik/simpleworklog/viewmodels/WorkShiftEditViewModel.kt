package com.scheffer.erik.simpleworklog.viewmodels

import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.database.repositories.WorkShiftRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkShiftEditViewModel(private val repository: WorkShiftRepository) : BaseCoroutineViewModel() {
    /**
     * Launching a new coroutine to persist the data in a non-blocking way
     */
    fun persist(workShift: WorkShift, onSave: () -> Unit) =
            scope.launch(Dispatchers.IO) {
                repository.persist(workShift)
                launch(Dispatchers.Main) { onSave() }
            }

    fun delete(workShift: WorkShift, onComplete: () -> Unit) =
            scope.launch(Dispatchers.IO) {
                repository.delete(workShift)
                launch(Dispatchers.Main) { onComplete() }
            }

    fun getWorkShift(id: Long) = repository.getById(id)
}