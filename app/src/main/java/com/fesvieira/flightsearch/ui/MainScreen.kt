package com.fesvieira.flightsearch.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fesvieira.flightsearch.model.Airport
import com.fesvieira.flightsearch.ui.components.AirportListItem
import com.fesvieira.flightsearch.ui.components.SearchTextField
import com.fesvieira.flightsearch.ui.theme.FlightSearchTheme
import com.fesvieira.flightsearch.ui.theme.Typography
import com.fesvieira.flightsearch.viewmodels.FlightSearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun MainScreen() {
    val flightSearchViewModel = hiltViewModel<FlightSearchViewModel>()
    val text by flightSearchViewModel.searchQuery.collectAsState()
    val airports by flightSearchViewModel.airportsList.collectAsState()

    Scaffold(topBar = { AppTopBar() }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            stickyHeader {
                SearchTextField(
                    value = text,
                    onValueChange = { flightSearchViewModel.searchQuery.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }

            airportsSection(
                airports = airports,
                lazyListScope = this
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Flight Search",
                style = Typography.headlineSmall,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFF205fa6)
        )
    )
}

fun airportsSection(
    airports: List<Airport>,
    lazyListScope: LazyListScope
) {
    lazyListScope.items(airports) { airport ->
        AirportListItem(airport)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {
    FlightSearchTheme {
        MainScreen()
    }
}