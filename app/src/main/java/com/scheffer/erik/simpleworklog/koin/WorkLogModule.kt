package com.scheffer.erik.simpleworklog.koin

import com.scheffer.erik.simpleworklog.database.WorkLogDatabase
import com.scheffer.erik.simpleworklog.database.repositories.WorkLogRepository
import com.scheffer.erik.simpleworklog.viewmodels.WorkLogEditViewModel
import com.scheffer.erik.simpleworklog.viewmodels.WorkLogListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val workLogModule = module {

    single { get<WorkLogDatabase>().workLogDao() }

    single { WorkLogRepository(get()) }

    viewModel { WorkLogEditViewModel(get()) }

    viewModel { WorkLogListViewModel(get()) }
}