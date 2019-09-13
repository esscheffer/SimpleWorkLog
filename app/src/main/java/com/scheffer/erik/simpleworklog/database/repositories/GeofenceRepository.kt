package com.scheffer.erik.simpleworklog.database.repositories

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.scheffer.erik.simpleworklog.database.daos.GeofenceDao
import com.scheffer.erik.simpleworklog.database.entities.GeofenceEntity
import com.scheffer.erik.simpleworklog.receiver.GeofenceBroadcastReceiver
import com.scheffer.erik.simpleworklog.utils.setCircularRegion

class GeofenceRepository(private val geofenceDao: GeofenceDao, private val context: Context) {
    private val geofencingClient = LocationServices.getGeofencingClient(context)
    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun getAll() = geofenceDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun persist(geofenceEntity: GeofenceEntity,
                        onSuccess: () -> Unit,
                        onFailure: (e: Exception) -> Unit) =
            buildGeofence(geofenceEntity).let { geofence ->
                if (ContextCompat.checkSelfPermission(context,
                                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    geofencingClient
                            .addGeofences(buildGeofencingRequest(geofence), geofencePendingIntent)
                            .addOnSuccessListener {
//                                geofenceDao.insert(geofenceEntity)
                                onSuccess()
                            }
                            .addOnFailureListener {
                                onFailure(it)
                            }
                }
            }

    private fun buildGeofence(geofenceEntity: GeofenceEntity) =
            Geofence.Builder()
                    .setRequestId(geofenceEntity.id)
                    .setCircularRegion(geofenceEntity)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                    .setExpirationDuration(Geofence.NEVER_EXPIRE)
                    .build()

    private fun buildGeofencingRequest(geofence: Geofence) =
            GeofencingRequest.Builder()
                    .setInitialTrigger(0)
                    .addGeofences(listOf(geofence))
                    .build()
}