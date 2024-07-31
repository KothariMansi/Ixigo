package com.example.ixigo.ui.trainsBySearch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ixigo.data.Train
import com.example.ixigo.data.TrainSearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainsBySearchScreen(
    navController: NavController,
    trainSearch: TrainSearch?,
    viewModel: TrainBySearchViewModel = hiltViewModel()
) {
    val trains = trainSearch?.let { viewModel.getTrains(it) }?.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            if (trainSearch != null) {
                CenterAlignedTopAppBar(
                    title = { Text(text = trainSearch.arrival +"  ->  "+ trainSearch.departure) },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.clickable { navController.popBackStack() }
                        )
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
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
                        TrainItem(trains = it)
                    }
                }
            }
        }
    }
}

@Composable
fun TrainItem(trains: Train, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Column {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = trains.trainNum.toString())
                Text(text = trains.trainBase.name)
            }
            Row {
                Text(text = trains.trainBase.trainSearch.arrival)
                Text(text = "  -  ")
                Text(text = trains.trainBase.trainSearch.arrival)
            }
        }
    }
}