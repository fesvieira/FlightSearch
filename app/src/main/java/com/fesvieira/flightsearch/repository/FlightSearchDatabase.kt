package com.fesvieira.flightsearch.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fesvieira.flightsearch.model.Airport
import com.fesvieira.flightsearch.model.Favorite

@Database(entities = [Airport::class, Favorite::class], version = 1)
abstract class FlightSearchDatabase: RoomDatabase() {
    abstract fun flightSearchDao(): FlightSearchDao
}