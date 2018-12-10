package com.scheffer.erik.simpleworklog

import android.app.Application
import android.content.Context
import com.scheffer.erik.simpleworklog.koin.databaseModule
import com.scheffer.erik.simpleworklog.koin.workLogModule
import com.scheffer.erik.simpleworklog.koin.workShiftModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    companion object {
        lateinit var application: Application
            private set

        val context: Context
            get() = application.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        // Start Koin
        startKoin(this, listOf(databaseModule, workLogModule, workShiftModule))
    }
}