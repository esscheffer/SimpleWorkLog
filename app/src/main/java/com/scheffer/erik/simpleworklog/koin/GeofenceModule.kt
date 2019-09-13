package com.scheffer.erik.simpleworklog.koin

import com.scheffer.erik.simpleworklog.database.WorkLogDatabase
import com.scheffer.erik.simpleworklog.database.repositories.GeofenceRepository
import com.scheffer.erik.simpleworklog.viewmodels.GeofenceConfigureViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val geofenceModule = module {

    single { get<WorkLogDatabase>().geofenceDao() }

    single { GeofenceRepository(get(), androidContext()) }

    viewModel { GeofenceConfigureViewModel(get()) }
}