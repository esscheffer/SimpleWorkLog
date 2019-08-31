package com.scheffer.erik.simpleworklog.viewmodels

import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.database.repositories.WorkLogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkLogEditViewModel(private val repository: WorkLogRepository)
    : BaseCoroutineViewModel() {

    fun persist(workLog: WorkLog, onSave: () -> Unit) =
            scope.launch(Dispatchers.IO) {
                repository.persist(workLog)
                launch(Dispatchers.Main) { onSave() }
            }

    fun delete(workLog: WorkLog, onComplete: () -> Unit) =
            scope.launch(Dispatchers.IO) {
                repository.delete(workLog)
                launch(Dispatchers.Main) { onComplete() }
            }

    fun getWorkLog(id: Long) = repository.getById(id)
}