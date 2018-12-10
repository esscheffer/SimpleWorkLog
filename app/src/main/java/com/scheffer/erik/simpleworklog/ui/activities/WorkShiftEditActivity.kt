package com.scheffer.erik.simpleworklog.ui.activities

import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.scheffer.erik.simpleutils.showDatePickDialog
import com.scheffer.erik.simpleutils.showTimePickDialog
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.utils.observeOnce
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftEditViewModel
import kotlinx.android.synthetic.main.activity_work_shift_edit.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

const val ARG_WORK_SHIFT_ID = "work_shift_id"

class WorkShiftEditActivity : AppCompatActivity() {
    private val workShiftEditViewModel by viewModel<WorkShiftEditViewModel>()
    private var workShift = WorkShift()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_shift_edit)

        intent.extras?.getLong(ARG_WORK_SHIFT_ID)?.let { workLogId ->
            workShiftEditViewModel.getWorkShift(workLogId).observeOnce(this, Observer {
                if (it != null) {
                    workShift = it
                    reloadUi()
                    delete_button.visibility = View.VISIBLE
                }
            })
        }

        start_shift_date_picker_editText.setOnClickListener {
            showDatePickDialog(workShift.enterTime) { reloadUi() }
        }

        start_shift_hour_picker_editText.setOnClickListener {
            showTimePickDialog(workShift.enterTime) { reloadUi() }
        }

        end_shift_date_picker_editText.setOnClickListener {
            showDatePickDialog(workShift.exitTime ?: Calendar.getInstance()) { pickedCalendar ->
                if (workShift.exitTime == null) {
                    workShift.exitTime = pickedCalendar
                }
                reloadUi()
            }
        }

        end_shift_hour_picker_editText.setOnClickListener {
            showTimePickDialog(workShift.exitTime ?: Calendar.getInstance()) {pickedCalendar ->
                if (workShift.exitTime == null) {
                    workShift.exitTime = pickedCalendar
                }
                reloadUi()
            }
        }

        clear_end_shift_button.setOnClickListener {
            workShift.exitTime = null
            reloadUi()
        }

        // Load an ad into the AdMob banner view.
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .build()
        adView.loadAd(adRequest)
    }

    private fun reloadUi() {
        val dateFormat = DateFormat.getDateFormat(this)
        val hourFormat = DateFormat.getTimeFormat(this)
        start_shift_date_picker_editText.setText(dateFormat.format(workShift.enterTime.time))
        start_shift_hour_picker_editText.setText(hourFormat.format(workShift.enterTime.time))
        val exitTime = workShift.exitTime
        if (exitTime != null) {
            end_shift_date_picker_editText.setText(dateFormat.format(workShift.enterTime.time))
            end_shift_hour_picker_editText.setText(hourFormat.format(workShift.enterTime.time))
        } else {
            end_shift_date_picker_editText.setText(R.string.on_going)
            end_shift_hour_picker_editText.setText(R.string.on_going)
        }
    }
}
