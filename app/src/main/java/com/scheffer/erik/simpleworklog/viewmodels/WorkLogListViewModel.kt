package com.scheffer.erik.simpleworklog.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.database.repositories.WorkLogRepository

class WorkLogListViewModel(repository: WorkLogRepository) : ViewModel() {
    val allWorkLogs: LiveData<List<WorkLog>> = repository.getAll()
}