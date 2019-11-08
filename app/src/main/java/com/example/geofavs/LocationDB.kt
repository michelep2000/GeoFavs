package com.example.geofavs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Location::class), version = 1)
abstract class LocationDB : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}

object LocationFactory {
    fun get(context: Context): LocationDB {
        return Room
            .databaseBuilder(
                context,
                LocationDB::class.java, "database-locations"
            ).build()
    }

}