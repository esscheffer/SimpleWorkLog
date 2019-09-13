package com.scheffer.erik.simpleworklog.utils

import android.content.Context
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.GeofenceStatusCodes
import com.scheffer.erik.simpleworklog.R

fun Context.getGeofenceErrorStringFromException(e: Exception): String =
        if (e is ApiException) {
            getGeofenceErrorStringFromCode(e.statusCode)
        } else {
            resources.getString(R.string.geofence_unknown_error)
        }

private fun Context.getGeofenceErrorStringFromCode(errorCode: Int) =
        when (errorCode) {
            GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE ->
                resources.getString(R.string.geofence_not_available)

            GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES ->
                resources.getString(R.string.geofence_too_many_geofences)

            GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS ->
                resources.getString(R.string.geofence_too_many_pending_intents)

            else -> resources.getString(R.string.geofence_unknown_error)
        }