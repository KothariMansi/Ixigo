package com.example.ixigo.ui.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ixigo.data.Platform
import com.example.ixigo.data.TrainSearch
import com.example.ixigo.ui.theme.IxigoTheme
import com.example.ixigo.ui.train.TrainViewModel

@Composable
fun SearchScreen(
    viewModel: TrainViewModel,
    ) {
    val state by viewModel.searchUiState.collectAsState()
    val platform = state.platforms.collectAsState(initial = emptyList()).value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        viewModel.updateIsArrivalIDepartureIsDate(
                            isArrival = false,
                            isDeparture = false
                        )
                    }
                    .padding(top = 16.dp)
            )
            //Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(50.dp),
                value = state.search, onValueChange = {},
                modifier = Modifier.width(350.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(platform) {
                SearchItem(platform = it) {
                    viewModel.updateArrivalOrDep(
                        it.station,
                        it.station
                    )
                }
            }
        }
    }
}

@Composable
fun SearchItem(
    platform: Platform,
    update:() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                update()
            }
            .padding(8.dp)
            .border(BorderStroke(1.dp, Color.Gray)),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
        ) {
            Text(text = platform.station + " " + "Jn")
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "(${platform.stationCode})")
        }
        Text(
            text = platform.station, style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )

    }
}

@Preview
@Composable
fun SearchItemPreview(){
    IxigoTheme {
        SearchItem(platform = Platform("BLT", "Balotra")) {
        }
    }
}