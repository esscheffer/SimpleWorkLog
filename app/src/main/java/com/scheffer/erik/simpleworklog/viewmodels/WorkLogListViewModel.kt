package com.scheffer.erik.simpleworklog.viewmodels

import androidx.lifecycle.LiveData
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.database.repositories.WorkLogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkLogListViewModel(private val repository: WorkLogRepository) : BaseCoroutineViewModel() {
    val allWorkLogs: LiveData<List<WorkLog>> = repository.getAll()

    fun persist(workLog: WorkLog) = scope.launch(Dispatchers.IO) {
        repository.persist(workLog)
    }
}