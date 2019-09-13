package com.scheffer.erik.simpleworklog.service

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class GeofenceTransitionsJobIntentService : JobIntentService() {

    companion object {
        private const val LOG_TAG = "GEOFENCE_SERVICE"

        private const val JOB_ID = 573

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context,
                    GeofenceTransitionsJobIntentService::class.java, JOB_ID,
                    intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        Log.i(LOG_TAG, "working...")
    }
}