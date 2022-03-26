package com.example.demosihappandroidpart.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demosihappandroidpart.core.Constants
import com.example.demosihappandroidpart.core.Screen
import com.example.demosihappandroidpart.presentation.components.CustomBottomNavigation
import com.example.demosihappandroidpart.presentation.get_all_workers_screen.GetAllWorkersScreen
import com.example.demosihappandroidpart.presentation.register_screen.RegisterScreen
import com.example.demosihappandroidpart.presentation.ui.theme.DemoSIHAppAndroidPartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){uri: Uri?->
        Log.d(Constants.HOME_SCREEN_TAG, "Uri returned is ${uri.toString()}")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DemoSIHAppAndroidPartTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val items = listOf(Screen.RegisterScreen, Screen.AllWorkersScreen)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        bottomBar = {
                            CustomBottomNavigation(navController = navController, items = items)
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.RegisterScreen.route
                        ) {
                            composable(route = Screen.RegisterScreen.route) {
                                RegisterScreen(getContent = getContent)
                            }
                            composable(route = Screen.AllWorkersScreen.route) {
                                GetAllWorkersScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

