package com.example.ixigo.ui.ixigoMoney

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.ixigo.DestinationsScreen
import com.example.ixigo.ui.BottomBar
import com.example.ixigo.ui.TopBar


@Composable
fun IxigoScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar("Money") },
        bottomBar = { BottomBar(
            navController,
            destinationsScreen = DestinationsScreen.IXIGO_MONEY
        ) }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

        }
    }

}