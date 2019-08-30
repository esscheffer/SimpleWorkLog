package com.scheffer.erik.simpleworklog.viewmodels

import androidx.lifecycle.LiveData
import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.database.repositories.WorkShiftRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkShiftListViewModel(private val repository: WorkShiftRepository) : BaseCoroutineViewModel() {
    val allWorkShifts: LiveData<List<WorkShift>> = repository.getAll()

    fun persist(workShift: WorkShift) = scope.launch(Dispatchers.IO) {
        repository.persist(workShift)
    }

    fun getOpenWorkShift() = repository.getOpenWorkShift()
}