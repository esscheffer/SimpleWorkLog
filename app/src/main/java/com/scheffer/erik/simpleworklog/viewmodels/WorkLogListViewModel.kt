package com.scheffer.erik.simpleworklog.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.database.repositories.WorkLogRepository

class WorkLogListViewModel(repository: WorkLogRepository) : ViewModel() {
    val allWorkLogs: LiveData<List<WorkLog>> = repository.getAll()
}