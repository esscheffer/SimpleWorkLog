package com.scheffer.erik.simpleworklog.utils

import com.google.android.gms.location.Geofence
import com.scheffer.erik.simpleworklog.database.entities.GeofenceEntity

fun Geofence.Builder.setCircularRegion(geofenceEntity: GeofenceEntity): Geofence.Builder =
        this.setCircularRegion(
                geofenceEntity.latLng.latitude,
                geofenceEntity.latLng.longitude,
                geofenceEntity.radius.toFloat())
