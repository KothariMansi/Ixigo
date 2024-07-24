package com.example.ixigo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ixigo.ui.ixigoMoney.IxigoScreen
import com.example.ixigo.ui.profile.ProfileScreen
import com.example.ixigo.ui.train.TrainScreen
import com.example.ixigo.ui.theme.IxigoTheme
import com.example.ixigo.ui.train.TrainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

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

    }
}
