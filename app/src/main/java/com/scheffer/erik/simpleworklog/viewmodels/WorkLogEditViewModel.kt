package com.scheffer.erik.simpleworklog.viewmodels

import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.database.repositories.WorkLogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkLogEditViewModel(private val repository: WorkLogRepository) : BaseCoroutineViewModel() {
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(workLog: WorkLog) = scope.launch(Dispatchers.IO) {
        repository.insert(workLog)
    }
}