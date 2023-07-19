package com.fesvieira.flightsearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("departure_code")
    val departure_code: String,
    @ColumnInfo("destination_code")
    val destination_code: String
)
