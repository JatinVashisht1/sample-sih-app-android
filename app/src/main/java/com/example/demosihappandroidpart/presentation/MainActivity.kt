package com.example.demosihappandroidpart.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.demosihappandroidpart.presentation.ui.theme.DemoSIHAppAndroidPartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoSIHAppAndroidPartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row {
                        GetData()
                    }
                }
            }
        }
    }
}

@Composable
fun GetData(viewModel: MainViewModel = hiltViewModel()) {
    Button(onClick = viewModel::getAllWorkers) {
        Text(text = "on click")
    }

    Button(onClick = {viewModel.postData()}) {
        Text(text = "post data")
    }
}