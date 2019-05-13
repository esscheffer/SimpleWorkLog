package com.scheffer.erik.simpleworklog.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import org.threeten.bp.OffsetDateTime

fun AppCompatActivity.showDatePickDialog(offsetDateTime: OffsetDateTime,
                                         onPick: (resultOffsetDateTime: OffsetDateTime) -> Unit) {
    DatePickerDialog(this, DatePickerDialog.OnDateSetListener
    { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
        onPick(offsetDateTime
                .withYear(year)
                .withMonth(month)
                .withDayOfMonth(dayOfMonth))
    }, offsetDateTime.year,
            offsetDateTime.monthValue,
            offsetDateTime.dayOfMonth)
            .show()
}

fun AppCompatActivity.showTimePickDialog(offsetDateTime: OffsetDateTime,
                                         onPick: (resultOffsetDateTime: OffsetDateTime) -> Unit) {
    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _: TimePicker, hour: Int, minute: Int ->
        onPick(offsetDateTime
                .withHour(hour)
                .withMinute(minute))
    }, offsetDateTime.hour, offsetDateTime.minute, true).show()
}