package com.scheffer.erik.simpleworklog.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.utils.*
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftEditViewModel
import kotlinx.android.synthetic.main.activity_work_shift_edit.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.OffsetDateTime

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
            showDatePickDialog(workShift.exitTime ?: OffsetDateTime.now()) { pickedCalendar ->
                if (workShift.exitTime == null) {
                    workShift.exitTime = pickedCalendar
                }
                reloadUi()
            }
        }

        end_shift_hour_picker_editText.setOnClickListener {
            showTimePickDialog(workShift.exitTime ?: OffsetDateTime.now()) { pickedCalendar ->
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

        save_button.setOnClickListener {
            workShiftEditViewModel.persist(workShift) {
                toast(R.string.work_shift_successful_save)
                finish()
            }
        }
        delete_button.setOnClickListener {
            workShiftEditViewModel.delete(workShift) {
                toast(R.string.work_shift_successful_delete)
                finish()
            }
        }

        reloadUi()

        // Load an ad into the AdMob banner view.
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .build()
        adView.loadAd(adRequest)
    }

    private fun reloadUi() {
        start_shift_date_picker_editText.setText(workShift.enterTime.getDefaultDateString())
        start_shift_hour_picker_editText.setText(workShift.enterTime.getDefaultTimeString())
        val exitTime = workShift.exitTime
        if (exitTime != null) {
            end_shift_date_picker_editText.setText(exitTime.getDefaultDateString())
            end_shift_hour_picker_editText.setText(exitTime.getDefaultTimeString())
        } else {
            end_shift_date_picker_editText.setText(R.string.on_going)
            end_shift_hour_picker_editText.setText(R.string.on_going)
        }
    }
}
