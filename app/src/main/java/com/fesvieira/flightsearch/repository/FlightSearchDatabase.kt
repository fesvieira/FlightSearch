package com.fesvieira.flightsearch.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fesvieira.flightsearch.model.Airport
import com.fesvieira.flightsearch.model.Favorite

@Database(entities = [Airport::class, Favorite::class], version = 2)
abstract class FlightSearchDatabase: RoomDatabase() {
    abstract fun flightSearchDao(): FlightSearchDao

    companion object {
        @Volatile
        private var INSTANCE: FlightSearchDatabase? = null

        fun getDatabase(
            context: Context
        ): FlightSearchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    FlightSearchDatabase::class.java,
                    "flight_search_database"
                )
                    .createFromAsset("database/flight_search.db")
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}