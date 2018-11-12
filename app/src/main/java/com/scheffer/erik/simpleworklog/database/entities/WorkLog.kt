package com.scheffer.erik.simpleworklog.database.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.scheffer.erik.simpleworklog.RegisterType
import java.util.*

@Entity
data class WorkLog(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                   var registerTime: Calendar = Calendar.getInstance(),
                   var registerType: RegisterType = RegisterType.CHECK_IN)