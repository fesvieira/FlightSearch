package com.fesvieira.flightsearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AIRPORT_LIST")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("iata_code")
    val iata_code: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("passengers")
    val passengers: Int
)
