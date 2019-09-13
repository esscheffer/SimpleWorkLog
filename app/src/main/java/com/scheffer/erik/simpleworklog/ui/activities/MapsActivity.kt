package com.scheffer.erik.simpleworklog.ui.activities

import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.database.entities.GeofenceEntity
import com.scheffer.erik.simpleworklog.utils.showGeofence
import kotlinx.android.synthetic.main.activity_maps.*
import org.jetbrains.anko.startActivity
import kotlin.math.roundToInt

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private var selectState = SelectState.SELECT_LOCATION
    private var geofenceEntity = GeofenceEntity()
    private var currentLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        center_current_location.setOnClickListener { centerCameraCurrentLocation() }

        select_radius_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                updateRadiusWithProgress(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        select_button.setOnClickListener {
            when (selectState) {
                SelectState.SELECT_LOCATION -> {
                    prepareUiSelectFenceRadious()
                    selectState = SelectState.SELECT_RADIUS
                }
                SelectState.SELECT_RADIUS -> {
                    goToConfigureFence()
                }
            }
        }

        new_location_button.setOnClickListener {
            prepareUiSelectLocation()
            selectState = SelectState.SELECT_LOCATION
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    currentLocation = location
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdate()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isMapToolbarEnabled = false

        updateRadiusWithProgress(select_radius_bar.progress)
        centerCameraCurrentLocation()
        prepareUiSelectLocation()
        selectState = SelectState.SELECT_LOCATION
    }

    private fun centerCameraCurrentLocation() {
        val location = currentLocation
        if (location != null) {
            map.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 15f))
            prepareUiSelectLocation()
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener {
                map.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 15f))
                prepareUiSelectLocation()
            }
        }
    }

    private fun updateRadiusWithProgress(progress: Int) {
        val radius = getRadius(progress)
        geofenceEntity.radius = radius
        radius_description.text = getString(R.string.radius_description, radius.roundToInt().toString())
        updateMapFence()
    }

    private fun getRadius(progress: Int) = (progress.toDouble() + 1) * 100

    private fun prepareUiSelectLocation() {
        instructions_title.setText(R.string.select_location)
        instruction_subtitle.visibility = View.VISIBLE
        marker_image.visibility = View.VISIBLE
        select_radius_bar.visibility = View.GONE
        radius_description.visibility = View.GONE
        new_location_button.visibility = View.GONE

        map.clear()
    }

    private fun prepareUiSelectFenceRadious() {
        instructions_title.setText(R.string.select_location)
        instruction_subtitle.visibility = View.GONE
        marker_image.visibility = View.GONE
        select_radius_bar.visibility = View.VISIBLE
        radius_description.visibility = View.VISIBLE
        new_location_button.visibility = View.VISIBLE

        geofenceEntity.latLng = map.cameraPosition.target
        updateMapFence()
    }

    private fun goToConfigureFence() {
        startActivity<GeofenceConfigureActivity>(GEOFENCE_ENTITY_KEY to geofenceEntity)
    }

    private fun updateMapFence() {
        map.clear()
        map.showGeofence(this, geofenceEntity)
    }

    private enum class SelectState { SELECT_LOCATION, SELECT_RADIUS }

    private fun startLocationUpdate() {
        LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }?.let {
            fusedLocationClient.requestLocationUpdates(it, locationCallback, Looper.getMainLooper())
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}
