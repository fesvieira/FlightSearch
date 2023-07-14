package com.fesvieira.flightsearch.repository

import androidx.room.Dao
import androidx.room.Query
import com.fesvieira.flightsearch.model.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightSearchDao {
    @Query("SELECT * FROM AIRPORT_LIST WHERE name = :name")
    fun searchAirports(name: String): Flow<List<Airport>>
}