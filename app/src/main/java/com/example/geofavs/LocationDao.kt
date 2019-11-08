package com.example.geofavs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocationDao{
    @Query("SELECT * FROM location")
    suspend fun getAll(): List<Location>

    @Insert
    suspend fun insertLocation(location: Location)
}