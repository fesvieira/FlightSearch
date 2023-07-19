package com.fesvieira.flightsearch.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fesvieira.flightsearch.model.Airport

@Composable
fun AirportListItem(airport: Airport, onClick: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .clickable {
                onClick(airport.iata_code)
            }
            .padding(vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Text(text = airport.iata_code, fontWeight = FontWeight.Bold)
        Text(text = airport.name, fontWeight = FontWeight.Light)
    }
}

@Preview
@Composable
fun PreviewAirportListItem() {
    AirportListItem(
        airport = Airport(1, "AOBA", "Aiport Of British Atlantic", passengers = 2),
        onClick = {})
}