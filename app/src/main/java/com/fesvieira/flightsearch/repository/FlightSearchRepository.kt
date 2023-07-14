package com.fesvieira.flightsearch.repository

import com.fesvieira.flightsearch.model.Airport
import kotlinx.coroutines.flow.Flow

class FlightSearchRepository(
    private val flightSearchDao: FlightSearchDao
): FlightSearchService {
    override suspend fun searchAirports(name: String): Flow<List<Airport>> {
        return flightSearchDao.searchAirports(name)
    }
}