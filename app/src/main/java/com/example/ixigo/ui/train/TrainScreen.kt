package com.example.ixigo.ui.train

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ixigo.DestinationsScreen
import com.example.ixigo.R
import com.example.ixigo.ui.BottomBar
import com.example.ixigo.ui.TopBar
import com.example.ixigo.ui.search.SearchScreen

@Composable
fun TrainScreen(
    navController: NavController,
    trainViewModel: TrainViewModel = hiltViewModel()
) {
    val state by trainViewModel.uiState.collectAsState()
    if (state.isArrival) {
        SearchScreen(trainViewModel)
    }
    else if (state.isDeparture){
        SearchScreen(viewModel = trainViewModel)
    }
    else {
    Scaffold(
        modifier =  Modifier.fillMaxSize(),
        topBar = { TopBar("Train") },
        bottomBar = { BottomBar(
            navController,
            destinationsScreen = DestinationsScreen.HOME
        ) },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = { trainViewModel.swap() },
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets(600, 0, 0, 1400))
                    .border(2.dp, Color.Gray, shape = RoundedCornerShape(60f)),
                containerColor = Color.White,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(pressedElevation = 2.dp),
            ) {
                Icon(painter = painterResource(id = R.drawable.swaparrow),
                    contentDescription = "",
                    modifier = Modifier.background(Color.White)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Card(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = Color.White)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(35f))
                //.height(215.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = state.arrival,
                    onValueChange = { },
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        trainViewModel.updateIsArrivalIDeparture(isDeparture = false, isArrival = true)
                                    }
                                }
                            }
                        },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.train), contentDescription = "")}

                )
                OutlinedTextField(
                    readOnly = true,
                    value = state.departure,
                    onValueChange = { },
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        trainViewModel.updateIsArrivalIDeparture(isDeparture = true, isArrival = false)
                                    }
                                }
                            }
                        },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.train), contentDescription = "")}

                )
                OutlinedTextField(
                    readOnly = true,
                    value = "Date", onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White),
                    leadingIcon = { Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = ""
                    )},
                    trailingIcon = { Icon(imageVector = Icons.Filled.Clear, contentDescription = "")}
                )
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.primary)

                ) {
                    Text(text = "Search Trains", modifier = Modifier, color = Color.White)
                }

            }


        }
    }

}
}