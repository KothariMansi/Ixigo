package com.example.ixigo.ui.train

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ixigo.DestinationsScreen
import com.example.ixigo.ui.BottomBar
import com.example.ixigo.ui.TopBar

@Composable
fun TrainScreen(
    navController: NavController,
    trainViewModel: TrainViewModel = hiltViewModel()
) {
    val state by trainViewModel.uiState.collectAsState()
    val trains = trainViewModel.trains.collectAsState(initial = emptyList())
    Scaffold(
        modifier =  Modifier.fillMaxSize()
        ,
        topBar = { TopBar("Train") },
        bottomBar = { BottomBar(
            navController,
            destinationsScreen = DestinationsScreen.HOME
        ) }
    ) {
        if (trains.value.isEmpty()){
            Text(text = "Empty List")
        } else {
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {
                items(trains.value){
                    Text(text = trains.value.toString())
                }
            }
        }

    }

}