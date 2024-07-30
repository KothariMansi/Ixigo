package com.example.ixigo.ui.train

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ixigo.DestinationsScreen
import com.example.ixigo.R
import com.example.ixigo.data.TrainSearch
import com.example.ixigo.ui.BottomBar
import com.example.ixigo.ui.TopBar
import com.example.ixigo.ui.navigateToSearch
import com.example.ixigo.ui.search.SearchScreen

@Composable
fun TrainScreen(
    navController: NavController,
    trainViewModel: TrainViewModel = hiltViewModel()
) {
    val state by trainViewModel.uiState.collectAsState()
    val calendar = Calendar.getInstance()
    val year: Int = calendar[java.util.Calendar.YEAR]
    val month: Int = calendar[java.util.Calendar.MONTH]
    val dayOfMonth: Int = calendar[java.util.Calendar.DAY_OF_MONTH]
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker,
          selectedYear: Int,
          selectedMonth: Int,
          selectedDayOfMonth: Int ->
            if (selectedMonth < 10) {
                trainViewModel.updatingCalender("$selectedDayOfMonth-0${selectedMonth + 1}-$selectedYear")// 24-07-2024
            } else {
                trainViewModel.updatingCalender("$selectedDayOfMonth-${selectedMonth + 1}-$selectedYear")// 24-07-2024
            }
        }, year, month, dayOfMonth
    )
    if (state.isArrival) {
        SearchScreen(trainViewModel)
    } else if (state.isDeparture) {
        SearchScreen(viewModel = trainViewModel)
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(stringResource(R.string.train)) },
            bottomBar = {
                BottomBar(
                    navController,
                    destinationsScreen = DestinationsScreen.HOME
                )
            },
            floatingActionButton = {
                SmallFloatingActionButton(
                    onClick = { trainViewModel.swap() },
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets(600, 0, 0, 1400))
                        .border(2.dp, Color.Gray, shape = RoundedCornerShape(60f)),
                    containerColor = Color.White,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
                        pressedElevation = 2.dp
                    ),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.swaparrow),
                        contentDescription = "",
                        modifier = Modifier.background(Color.White)
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Card(
                    modifier = Modifier

                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(color = Color.White)
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(35f))
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
                                        interactionSource.interactions.collect { interaction ->
                                            if (interaction is PressInteraction.Release) {
                                                trainViewModel.updateIsArrivalIDepartureIsDate(
                                                    isDeparture = false,
                                                    isArrival = true
                                                )
                                            }
                                        }
                                    }
                                },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.train),
                                    contentDescription = ""
                                )
                            }

                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = state.departure,
                            onValueChange = { },
                            interactionSource = remember { MutableInteractionSource() }
                                .also { interactionSource ->
                                    LaunchedEffect(interactionSource) {
                                        interactionSource.interactions.collect { interaction ->
                                            if (interaction is PressInteraction.Release) {
                                                trainViewModel.updateIsArrivalIDepartureIsDate(
                                                    isDeparture = true,
                                                    isArrival = false
                                                )
                                            }
                                        }
                                    }
                                },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.train),
                                    contentDescription = ""
                                )
                            }

                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = state.selectedDateText, onValueChange = {},
                            interactionSource = remember { MutableInteractionSource() }
                                .also { interactionSource ->
                                    LaunchedEffect(interactionSource) {
                                        interactionSource.interactions.collect { interaction ->
                                            if (interaction is PressInteraction.Release) {
                                                trainViewModel.updateIsArrivalIDepartureIsDate(
                                                    isDateSelected = true
                                                )
                                            }
                                        }
                                    }
                                },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.calendar),
                                    contentDescription = ""
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        trainViewModel.updatingCalender("Date")
                                    }
                                )
                            }
                        )
                        if (state.isDateSelected) {
                            datePickerDialog.show()
                            trainViewModel.updateIsArrivalIDepartureIsDate(isDateSelected = false)
                        }
                        TextButton(
                            onClick = {
                                navigateToSearch(
                                    navController,
                                    TrainSearch(
                                        state.arrival,
                                        state.departure,
                                        state.selectedDateText
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(MaterialTheme.colorScheme.primary)

                        ) {
                            Text(
                                text = stringResource(R.string.search_trains), modifier = Modifier, color = Color.White,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = Color.Black
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)) {
                            Icon(painter = painterResource(id = R.drawable.location), contentDescription = "")
                            Column(modifier = Modifier.padding(top = 2.dp)) {
                                Text(text = stringResource(R.string.running_status), modifier = Modifier, style = MaterialTheme.typography.labelMedium)
                                Text(text = stringResource(R.string.where_s_my_train), modifier = Modifier, style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = Color.Black
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ticket), contentDescription = "")
                            Column(
                                modifier = Modifier.padding(top = 2.dp)
                            ) {
                                Text(text = stringResource(R.string.pnr_status), modifier = Modifier, style = MaterialTheme.typography.labelMedium)
                                Text(text = stringResource(R.string.where_s_my_trip_status), modifier = Modifier, style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
            }
        }
    }
}