package com.fesvieira.flightsearch.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.fesvieira.flightsearch.ui.components.SearchTextField
import com.fesvieira.flightsearch.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(topBar = { AppTopBar() }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .padding(paddingValues)
        ) {
            stickyHeader {
                SearchTextField()
            }
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

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}