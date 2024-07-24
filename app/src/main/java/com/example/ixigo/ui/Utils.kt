package com.example.ixigo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ixigo.DestinationsScreen
import com.example.ixigo.R
import com.example.ixigo.ui.theme.IxigoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    name: String
) {
    TopAppBar(title = { Text(text = name) })
}

@Composable
fun BottomBar(
    navController: NavController,
    destinationsScreen: DestinationsScreen
) {

    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        navController.navigate(DestinationsScreen.HOME.name)
                    }
                    .background(
                        color =
                        if (destinationsScreen == DestinationsScreen.HOME) MaterialTheme.colorScheme.primaryContainer
                        else Color.Unspecified, shape = RoundedCornerShape(15.dp)
                    )
                    .padding(4.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ixigo_money), contentDescription = "",
                modifier = Modifier
                    .clickable {
                        navController.navigate(DestinationsScreen.IXIGO_MONEY.name)
                    }
                    .background(
                        color =
                        if (destinationsScreen == DestinationsScreen.IXIGO_MONEY) MaterialTheme.colorScheme.primaryContainer
                        else Color.Unspecified, shape = RoundedCornerShape(15.dp)
                    )
                    .padding(4.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.profile), contentDescription = "",
                modifier = Modifier
                    .clickable {
                        navController.navigate(DestinationsScreen.PROFILE.name)
                    }
                    .background(
                        color =
                        if (destinationsScreen == DestinationsScreen.PROFILE) MaterialTheme.colorScheme.primaryContainer
                        else Color.Unspecified, shape = RoundedCornerShape(15.dp)
                    )
                    .padding(4.dp)
            )
        }
    }
}

@Composable
@Preview
fun TopBarPreview() {
    IxigoTheme {
        TopBar(name = "Text")
    }
}

@Composable
@Preview
fun BottomBarPreview() {
    IxigoTheme {
        BottomBar(
            navController = NavController(LocalContext.current),
            destinationsScreen = DestinationsScreen.HOME
        )
    }
}