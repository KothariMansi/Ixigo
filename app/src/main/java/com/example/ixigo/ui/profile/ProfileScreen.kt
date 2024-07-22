package com.example.ixigo.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ixigo.DestinationsScreen
import com.example.ixigo.ui.BottomBar
import com.example.ixigo.ui.TopBar

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { TopBar(
            "Profile"
        ) },
        bottomBar = { BottomBar(
            navController,
            DestinationsScreen.PROFILE
        ) }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

        }
    }

}