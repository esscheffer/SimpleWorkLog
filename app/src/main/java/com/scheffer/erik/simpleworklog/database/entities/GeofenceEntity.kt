package com.scheffer.erik.simpleworklog.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class GeofenceEntity(@PrimaryKey val id: String = UUID.randomUUID().toString(),
                          var name: String = "",
                          var latLng: LatLng = LatLng(0.0, 0.0),
                          var radius: Double = 100.0,
                          var registerClockIn: Boolean = false,
                          var registerClockOut: Boolean = false,
                          var registerCheckIn: Boolean = false,
                          var registerCheckOut: Boolean = false) : Parcelable