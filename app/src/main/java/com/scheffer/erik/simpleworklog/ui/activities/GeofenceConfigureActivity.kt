package com.scheffer.erik.simpleworklog.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.database.entities.GeofenceEntity
import com.scheffer.erik.simpleworklog.utils.getGeofenceErrorStringFromException
import com.scheffer.erik.simpleworklog.viewmodels.GeofenceConfigureViewModel
import kotlinx.android.synthetic.main.activity_geofence_configure.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

const val GEOFENCE_ENTITY_KEY = "geofence_key"

class GeofenceConfigureActivity : AppCompatActivity() {
    private val geofenceConfigureViewModel by viewModel<GeofenceConfigureViewModel>()
    lateinit var geofenceEntity: GeofenceEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geofence_configure)

        val geofenceFromIntent: GeofenceEntity? = intent.extras?.getParcelable(GEOFENCE_ENTITY_KEY)

        // This should never happen
        if (geofenceFromIntent == null) {
            longToast(R.string.no_geofence_to_configure)
            finish()
        } else {
            geofenceEntity = geofenceFromIntent
        }

        save_button.setOnClickListener {
            setUiFieldsToGeofenceVariable()
            geofenceConfigureViewModel.persist(geofenceEntity,
                    onSave = {
                        toast(R.string.area_register_success)
                        finish()
                    },
                    onFailure = {
                        longToast(getGeofenceErrorStringFromException(it))
                    })
        }
    }

    private fun setUiFieldsToGeofenceVariable() {
        with(geofenceEntity) {
            name = name_text.text.toString()
            registerClockIn = clock_in_checkbox.isChecked
            registerClockOut = clock_out_checkbox.isChecked
            registerCheckIn = start_shift_checkbox.isChecked
            registerCheckOut = end_shift_checkbox.isChecked
        }
    }
}
