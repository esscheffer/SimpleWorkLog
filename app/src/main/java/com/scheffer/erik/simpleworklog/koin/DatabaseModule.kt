package com.scheffer.erik.simpleworklog.koin

import androidx.room.Room
import com.scheffer.erik.simpleworklog.database.DATABASE_NAME
import com.scheffer.erik.simpleworklog.database.WorkLogDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    // Room database
    single {
        Room.databaseBuilder(androidApplication(), WorkLogDatabase::class.java, DATABASE_NAME)
                .build()
    }
}