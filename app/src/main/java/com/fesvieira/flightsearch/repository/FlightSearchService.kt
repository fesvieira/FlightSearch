package com.fesvieira.flightsearch.repository

import com.fesvieira.flightsearch.model.Airport
import kotlinx.coroutines.flow.Flow

interface FlightSearchService {
    suspend fun searchAirports(name: String): Flow<List<Airport>>
}