package com.fesvieira.flightsearch.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fesvieira.flightsearch.R
import com.fesvieira.flightsearch.model.Airport
import com.fesvieira.flightsearch.ui.theme.DarkGrey
import com.fesvieira.flightsearch.ui.theme.DarkOrange
import com.fesvieira.flightsearch.ui.theme.LightGrey

@Composable
fun FlightCard(
    isFavorite: Boolean,
    departAirport: Airport,
    arriveAirport: Airport,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(
                LightGrey, AbsoluteRoundedCornerShape(topRight = 16.dp)
            )
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "DEPART", fontWeight = FontWeight.Medium, fontSize = 12.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = departAirport.iata_code, fontWeight = FontWeight.Bold)
                Text(text = departAirport.name, fontWeight = FontWeight.Light)
            }

            Text(text = "ARRIVE", fontWeight = FontWeight.Medium, fontSize = 12.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = arriveAirport.iata_code, fontWeight = FontWeight.Bold)
                Text(text = arriveAirport.name, fontWeight = FontWeight.Light)
            }
        }

        Icon(
            painterResource(R.drawable.ic_star),
            contentDescription = null,
            tint = if (isFavorite) DarkOrange else DarkGrey,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun PreviewFlightCard() {
    FlightCard(
        isFavorite = true,
        departAirport = Airport(1, "AOBA", "Aiport Of British Atlantic", passengers = 2),
            arriveAirport = Airport(1, "AOBA", "Aiport Of British Atlantic", passengers = 2)
    )
}