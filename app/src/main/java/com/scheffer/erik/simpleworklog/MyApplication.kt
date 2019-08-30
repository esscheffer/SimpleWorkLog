package com.scheffer.erik.simpleworklog

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.jakewharton.threetenabp.AndroidThreeTen
import com.scheffer.erik.simpleworklog.koin.databaseModule
import com.scheffer.erik.simpleworklog.koin.workLogModule
import com.scheffer.erik.simpleworklog.koin.workShiftModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : MultiDexApplication() {

    companion object {
        lateinit var application: Application
            private set

        val context: Context
            get() = application.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        // Start ThreeTenABP
        AndroidThreeTen.init(this)

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(databaseModule, workLogModule, workShiftModule))
        }
    }
}