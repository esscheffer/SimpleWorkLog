package com.scheffer.erik.simpleworklog.database.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class WorkShift(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                     var enterTime: Calendar = Calendar.getInstance(),
                     var exitTime: Calendar? = null)