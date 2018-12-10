package com.scheffer.erik.simpleutils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.util.*

fun AppCompatActivity.showDatePickDialog(calendar: Calendar,
                                         onPick: (resultCalendar: Calendar) -> Unit) {
    DatePickerDialog(this, DatePickerDialog.OnDateSetListener
    { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        onPick(calendar)
    }, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
            .show()
}

fun AppCompatActivity.showTimePickDialog(calendar: Calendar,
                                         onPick: (resultCalendar: Calendar) -> Unit) {
    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _: TimePicker, hour: Int, minute: Int ->
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        onPick(calendar)
    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
}