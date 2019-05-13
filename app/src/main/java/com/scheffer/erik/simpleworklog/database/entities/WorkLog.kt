package com.scheffer.erik.simpleworklog.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.scheffer.erik.simpleworklog.RegisterType
import org.threeten.bp.OffsetDateTime

@Entity
data class WorkLog(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                   var registerTime: OffsetDateTime = OffsetDateTime.now(),
                   var registerType: RegisterType = RegisterType.CLOCK_IN)