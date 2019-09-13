package com.scheffer.erik.simpleworklog.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scheffer.erik.simpleworklog.database.entities.GeofenceEntity

@Dao
interface GeofenceDao {
    @Query("SELECT * FROM GeofenceEntity")
    fun getAll(): LiveData<List<GeofenceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(geofenceEntity: GeofenceEntity)
}