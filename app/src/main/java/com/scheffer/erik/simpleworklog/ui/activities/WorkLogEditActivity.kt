package com.scheffer.erik.simpleworklog.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.RegisterType
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.utils.*
import com.scheffer.erik.simpleworklog.viewmodels.WorkLogEditViewModel
import kotlinx.android.synthetic.main.activity_work_log_edit.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


const val ARG_WORK_LOG_ID = "work_log_id"

class WorkLogEditActivity : AppCompatActivity() {
    private val workLogEditViewModel by viewModel<WorkLogEditViewModel>()
    private var workLog = WorkLog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_log_edit)

        intent.extras?.getLong(ARG_WORK_LOG_ID)?.let { workLogId ->
            workLogEditViewModel.getWorkLog(workLogId).observeOnce(this, Observer {
                if (it != null) {
                    workLog = it
                    reloadUi()
                    delete_button.visibility = View.VISIBLE
                }
            })
        }

        date_picker_editText.setOnClickListener {
            showDatePickDialog(workLog.registerTime) { reloadUi() }
        }

        hour_picker_editText.setOnClickListener {
            showTimePickDialog(workLog.registerTime) { reloadUi() }
        }

        val adapter = ArrayAdapter<RegisterType>(this,
                android.R.layout.simple_spinner_item,
                RegisterType.values())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        workLog_type_spinner.setItems(RegisterType.values().toList())
        workLog_type_spinner.setOnItemSelectedListener { _, _, _, item ->
            workLog.registerType = item as RegisterType
        }

        save_button.setOnClickListener {
            workLogEditViewModel.persist(workLog) {
                toast(R.string.work_log_successful_save)
                finish()
            }
        }
        delete_button.setOnClickListener {
            workLogEditViewModel.delete(workLog) {
                toast(R.string.work_log_successful_delete)
                finish()
            }
        }

        reloadUi()

        // Load an ad into the AdMob banner view.
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .build()
        adView?.loadAd(adRequest)
    }

    private fun reloadUi() {
        date_picker_editText.setText(workLog.registerTime.getDefaultDateString())
        hour_picker_editText.setText(workLog.registerTime.getDefaultTimeString())
        workLog_type_spinner.selectedIndex = workLog.registerType.ordinal
    }
}
