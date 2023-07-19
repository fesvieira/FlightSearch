package com.fesvieira.flightsearch.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Query
import com.fesvieira.flightsearch.model.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightSearchDao {
    @Query("SELECT * FROM airport")
    suspend fun getAll(): List<Airport>

    @Query("SELECT * FROM airport WHERE name = :name")
    fun searchAirports(name: String): Flow<List<Airport>>
}