package com.example.ixigo.ui.trainsBySearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ixigo.data.TrainSearch

@Composable
fun TrainsBySearchScreen(
    navController: NavController,
    trainSearch: TrainSearch?,
    viewModel: TrainBySearchViewModel = hiltViewModel()
) {
    val trains = trainSearch?.let { viewModel.getTrains(it) }?.collectAsState(initial = emptyList())
    if (trains != null) {
        if (trains.value.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "No Trains")
            }
        }
        LazyColumn{
            items(trains.value) {
                Text(text = it.toString())
            }
        }
    }


}