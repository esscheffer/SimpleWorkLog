package com.scheffer.erik.simpleworklog.koin

import com.scheffer.erik.simpleworklog.database.WorkLogDatabase
import com.scheffer.erik.simpleworklog.database.repositories.WorkShiftRepository
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftEditViewModel
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val workShiftModule = module {

    single { get<WorkLogDatabase>().workShiftDao() }

    single { WorkShiftRepository(get()) }

    viewModel { WorkShiftEditViewModel(get()) }

    viewModel { WorkShiftListViewModel(get()) }
}