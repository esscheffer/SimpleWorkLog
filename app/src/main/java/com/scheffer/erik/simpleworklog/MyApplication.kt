package com.scheffer.erik.simpleworklog

import android.app.Application
import com.scheffer.erik.simpleworklog.koin.databaseModule
import com.scheffer.erik.simpleworklog.koin.workLogModule
import com.scheffer.erik.simpleworklog.koin.workShiftModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(this, listOf(databaseModule, workLogModule, workShiftModule))
    }
}