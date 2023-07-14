package com.fesvieira.flightsearch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fesvieira.flightsearch.model.Airport
import com.fesvieira.flightsearch.repository.FlightSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightSearchViewModel @Inject constructor(
    private val flightSearchRepository: FlightSearchRepository
): ViewModel() {

    private val _airportsList = MutableStateFlow<List<Airport>>(emptyList())
    val airportsList get() = _airportsList

    fun searchAirports(query: String) = viewModelScope.launch {
        flightSearchRepository.searchAirports(query)
    }
}