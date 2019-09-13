package com.scheffer.erik.simpleworklog.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.database.entities.GeofenceEntity

fun GoogleMap.showGeofence(context: Context, geofenceEntity: GeofenceEntity) {
    val marker = addMarker(MarkerOptions()
            .position(geofenceEntity.latLng)
            .icon(vectorToBitmap(context.resources, R.drawable.ic_location_on_black_48dp)))
    marker.tag = geofenceEntity.id
    addCircle(CircleOptions()
            .center(geofenceEntity.latLng)
            .radius(geofenceEntity.radius)
            .strokeColor(ContextCompat.getColor(context, R.color.colorAccent))
            .fillColor(ContextCompat.getColor(context, R.color.colorFenceFill)))
}

private fun vectorToBitmap(resources: Resources, @DrawableRes id: Int): BitmapDescriptor {
    val vectorDrawable = ResourcesCompat.getDrawable(resources, id, null)
    val bitmap = Bitmap.createBitmap(vectorDrawable!!.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}