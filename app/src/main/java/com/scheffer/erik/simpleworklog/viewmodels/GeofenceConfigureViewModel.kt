package com.scheffer.erik.simpleworklog.viewmodels

import com.scheffer.erik.simpleworklog.database.entities.GeofenceEntity
import com.scheffer.erik.simpleworklog.database.repositories.GeofenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeofenceConfigureViewModel(private val repository: GeofenceRepository)
    : BaseCoroutineViewModel() {

    fun persist(geofenceEntity: GeofenceEntity,
                onSave: () -> Unit,
                onFailure: (e: Exception) -> Unit) =
            scope.launch(Dispatchers.IO) {
                repository.persist(geofenceEntity,
                        onSuccess = {
                            launch(Dispatchers.Main) { onSave() }
                        },
                        onFailure = {
                            launch(Dispatchers.Main) { onFailure(it) }
                        })
            }
}