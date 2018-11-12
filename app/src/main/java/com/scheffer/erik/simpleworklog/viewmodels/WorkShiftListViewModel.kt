package com.scheffer.erik.simpleworklog.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.database.repositories.WorkShiftRepository

class WorkShiftListViewModel(repository: WorkShiftRepository) : ViewModel() {
    val allWorkShifts: LiveData<List<WorkShift>> = repository.getAll()
}