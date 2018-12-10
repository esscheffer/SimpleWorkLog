package com.scheffer.erik.simpleworklog.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.RegisterType
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.ui.MainPageAdapter
import com.scheffer.erik.simpleworklog.viewmodels.WorkLogEditViewModel
import com.scheffer.erik.simpleworklog.viewmodels.WorkLogListViewModel
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftEditViewModel
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
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
            if (workLogs.isNullOrEmpty()) {
                // if there is no data, persist fake data
                val totalFakeData = 11
                val c = Calendar.getInstance()
                c.set(Calendar.HOUR_OF_DAY, 8)
                c.set(Calendar.MINUTE, 0)
                c.set(Calendar.SECOND, 0)
                c.add(Calendar.DAY_OF_YEAR, -totalFakeData)

                for (i in 1..totalFakeData) {
                    val workLog = WorkLog()
                    workLog.registerTime = c.clone() as Calendar
                    workLog.registerType = RegisterType.CLOCK_IN
                    workLogEditViewModel.persist(workLog) {}

                    if (i != totalFakeData) {
                        val workLogOut = WorkLog()
                        workLogOut.registerTime = (c.clone() as Calendar).apply { add(Calendar.HOUR_OF_DAY, 8) }
                        workLogOut.registerType = RegisterType.CLOCK_OUT
                        workLogEditViewModel.persist(workLogOut) {}
                    }
                    c.add(Calendar.DAY_OF_YEAR, 1)
                }
            }
            Log.i("WOKRLOG", "$workLogs")
        })

        workShiftListViewModel.allWorkShifts.observe(this, Observer { workShifts ->
            if (workShifts.isNullOrEmpty()) {
                // if there is no data, persist fake data
                val totalFakeData = 10
                val c = Calendar.getInstance()
                c.set(Calendar.HOUR_OF_DAY, 8)
                c.set(Calendar.MINUTE, 0)
                c.set(Calendar.SECOND, 0)
                c.add(Calendar.DAY_OF_YEAR, -totalFakeData)

                for (i in 1..totalFakeData) {
                    val workShift = WorkShift()
                    workShift.enterTime = c.clone() as Calendar
                    if (i != totalFakeData) {
                        workShift.exitTime = (c.clone() as Calendar).apply { add(Calendar.HOUR_OF_DAY, 8) }
                    }
                    workShiftEditViewModel.persist(workShift)
                    c.add(Calendar.DAY_OF_YEAR, 1)
                }
            }
            Log.i("WORKSHIFT", "$workShifts")
        })

        val fragmentAdapter = MainPageAdapter(supportFragmentManager, this)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }
}
