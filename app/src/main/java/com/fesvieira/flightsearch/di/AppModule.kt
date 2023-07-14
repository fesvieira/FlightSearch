package com.fesvieira.flightsearch.di

import android.content.Context
import androidx.room.Room
import com.fesvieira.flightsearch.repository.FlightSearchDao
import com.fesvieira.flightsearch.repository.FlightSearchDatabase
import com.fesvieira.flightsearch.repository.FlightSearchRepository
import com.fesvieira.flightsearch.repository.FlightSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationContext::class)
object AppModule {
    @Provides
    fun provideFlightSearchDb(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        FlightSearchDatabase::class.java,
        "FLIGHT_TABLE"
    ).build()

    @Provides
    fun provideFlightSearchDao(
        flightDb: FlightSearchDatabase
    ) = flightDb.flightSearchDao()

    @Provides
    fun provideFlightSearchRepository(
        flightSearchDao: FlightSearchDao
    ): FlightSearchService = FlightSearchRepository(flightSearchDao)
}