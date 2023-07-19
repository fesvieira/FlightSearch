package com.fesvieira.flightsearch.repository

import com.fesvieira.flightsearch.model.Airport
import kotlinx.coroutines.flow.Flow

class FlightSearchRepositoryImpl(
    private val flightSearchDao: FlightSearchDao
): FlightSearchRepository {
    override suspend fun getAll(): List<Airport> {
        return flightSearchDao.getAll()
    }
    override suspend fun searchAirports(name: String): Flow<List<Airport>> {
        return flightSearchDao.searchAirports(name)
    }
}