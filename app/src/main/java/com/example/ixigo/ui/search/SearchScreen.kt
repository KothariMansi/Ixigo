package com.example.ixigo.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ixigo.ui.train.TrainViewModel

@Composable
fun SearchScreen(
    viewModel: TrainViewModel,
    ) {
    val state by viewModel.searchUiState.collectAsState()
    val trainSearch = state.platforms.collectAsState(initial = emptyList()).value
    Column {
        Row {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable {
                }
            )
            TextField(value = "", onValueChange = {})
        }

        LazyColumn {
            items(trainSearch) {
                Card(
                    modifier = Modifier.clickable {
                        viewModel.updateArrivalOrDep(
                            it.station,
                            it.station
                        )
                    }
                ) {
                    Text(text = it.stationCode)
                    Text(text = it.station)
                }
            }
        }
        // Toast.makeText(LocalContext.current, trainState.arrival + trainState.departure, Toast.LENGTH_SHORT).show()

    }
}