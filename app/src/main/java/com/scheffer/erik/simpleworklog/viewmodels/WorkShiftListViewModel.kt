package com.scheffer.erik.simpleworklog.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.database.repositories.WorkShiftRepository

class WorkShiftListViewModel(repository: WorkShiftRepository) : ViewModel() {
    val allWorkShifts: LiveData<List<WorkShift>> = repository.getAll()
}