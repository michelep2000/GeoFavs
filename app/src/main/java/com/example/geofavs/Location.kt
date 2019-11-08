package com.example.geofavs

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val longitude: Double,
    val latitude: Double
    )