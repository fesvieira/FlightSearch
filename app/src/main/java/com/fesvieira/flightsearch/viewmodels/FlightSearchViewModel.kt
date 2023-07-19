package com.fesvieira.flightsearch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fesvieira.flightsearch.model.Airport
import com.fesvieira.flightsearch.repository.FlightSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class FlightSearchViewModel @Inject constructor(
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery

    @OptIn(FlowPreview::class)
    val airportsList: StateFlow<List<Airport>> = _searchQuery.debounce(1000).flatMapLatest { query ->
        flightSearchRepository.searchAirports(query)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}