package com.fesvieira.flightsearch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fesvieira.flightsearch.model.Airport
import com.fesvieira.flightsearch.repository.FlightSearchRepository
import com.fesvieira.flightsearch.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class FlightSearchViewModel @Inject constructor(
    private val flightSearchRepository: FlightSearchRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private var _allAirports = emptyList<Airport>()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery

    private val _flights = MutableStateFlow<List<Pair<Airport, Airport>>>(emptyList())
    val flights get() = _flights

    init {
        viewModelScope.launch {
            _searchQuery.value = userPreferencesRepository.lastSearch.first()
            _allAirports = flightSearchRepository.searchAirports("").first()
            searchAirportFlights(userPreferencesRepository.lastSelected.first() ?: return@launch)
        }
    }

    @OptIn(FlowPreview::class)
    private var _airportsList: StateFlow<List<Airport>> = _searchQuery.debounce(1000).flatMapLatest { query ->
        userPreferencesRepository.saveLastSearch(query)
        flightSearchRepository.searchAirports(query)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val airportsList get() = _airportsList


    fun searchAirportFlights(airportCode: String) {
        viewModelScope.launch {
            val departAirport = _allAirports.firstOrNull { it.iata_code == airportCode } ?: return@launch
            _searchQuery.value = departAirport.name
            userPreferencesRepository.saveSelectedAirport(departAirport.iata_code)
            _flights.value = _allAirports.dropWhile { it.iata_code == airportCode }.map {
                Pair(departAirport, it)
            }
        }
    }

    fun clearAirport() {
        viewModelScope.launch {
            _searchQuery.value = ""
            delay(1000)
            _flights.value = emptyList()
            userPreferencesRepository.saveLastSearch("")
            userPreferencesRepository.saveSelectedAirport(null)
        }
    }
}