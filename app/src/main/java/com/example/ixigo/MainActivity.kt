package com.example.ixigo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ixigo.data.TrainSearch
import com.example.ixigo.ui.ixigoMoney.IxigoScreen
import com.example.ixigo.ui.profile.ProfileScreen
import com.example.ixigo.ui.theme.IxigoTheme
import com.example.ixigo.ui.train.TrainScreen
import com.example.ixigo.ui.trainsBySearch.TrainsBySearchScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IxigoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IxigoApp()
                }
            }
        }
    }
}

enum class DestinationsScreen{
    HOME,
    IXIGO_MONEY,
    TRIPS,
    CONTACT_US,
    PROFILE,
    TRAIN
}

@Composable
fun IxigoApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationsScreen.HOME.name) {
        composable(DestinationsScreen.HOME.name){
            TrainScreen(navController)
        }
        composable(DestinationsScreen.IXIGO_MONEY.name){
            IxigoScreen(navController)
        }
        composable(DestinationsScreen.PROFILE.name){
            ProfileScreen(navController)
        }
        composable(
            route = DestinationsScreen.TRAIN.name + "/{trainSearch}",
            arguments = listOf(navArgument("trainSearch") { type = NavType.StringType })
        ) { backStackEntry ->
            val trainSearchJson = backStackEntry.arguments?.getString("trainSearch")
            val trainSearch = trainSearchJson?.let { Json.decodeFromString<TrainSearch>(it) }
            TrainsBySearchScreen(navController, trainSearch)
        }

    }
}
