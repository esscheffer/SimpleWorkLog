package com.scheffer.erik.simpleworklog

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.viewmodels.WorkLogEditViewModel
import com.scheffer.erik.simpleworklog.viewmodels.WorkLogListViewModel
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftEditViewModel
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    private val workLogEditViewModel by viewModel<WorkLogEditViewModel>()
    private val workShiftEditViewModel by viewModel<WorkShiftEditViewModel>()

    private val workLogListViewModel by viewModel<WorkLogListViewModel>()
    private val workShiftListViewModel by viewModel<WorkShiftListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workLogListViewModel.allWorkLogs.observe(this, Observer { workLogs ->
            workLogs?.let { Log.i("WOKRLOG", "$workLogs") }
        })

        workShiftListViewModel.allWorkShifts.observe(this, Observer { workShifts ->
            workShifts?.let { Log.i("WORKSHIFT", "$workShifts") }
        })

        add_button.setOnClickListener {
            workLogEditViewModel.insert(WorkLog(null, Calendar.getInstance(), RegisterType.CHECK_OUT))
            workShiftEditViewModel.insert(WorkShift(null, Calendar.getInstance(), Calendar.getInstance()))
        }
    }
}
