package com.scheffer.erik.simpleworklog.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity
data class WorkShift(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                     var enterTime: OffsetDateTime = OffsetDateTime.now(),
                     var exitTime: OffsetDateTime? = null)