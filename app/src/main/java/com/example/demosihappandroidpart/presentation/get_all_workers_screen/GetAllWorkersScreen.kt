package com.example.demosihappandroidpart.presentation.get_all_workers_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.demosihappandroidpart.domain.model.model_all_workers.ModelAllWorkersItem
import com.example.demosihappandroidpart.presentation.get_all_workers_screen.components.AllWorkersScreenState

@Composable
fun GetAllWorkersScreen(
    viewModel: GetAllWorkersViewModel = hiltViewModel()
) {
    val allWorkersState =
        viewModel.allWorkersState.observeAsState(initial = AllWorkersScreenState()).value
    val listState = rememberLazyListState()
//    ShowAllWorkers(allWorkersScreenState = allWorkersState, lazyListState = listState)
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            allWorkersState.error.isNotBlank() -> {
                Text(
                    text = allWorkersState.error,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
            allWorkersState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                ShowAllWorkers(
                    allWorkers = allWorkersState.allWorkers.allWorkers,
                    lazyListState = listState,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun ShowAllWorkers(
    modifier: Modifier = Modifier,
    allWorkers: List<ModelAllWorkersItem>,
    lazyListState: LazyListState,
) {

    LazyColumn(state = lazyListState, modifier = modifier) {
        items(items = allWorkers) { worker ->
//            Log.d("GetAllWorkersScreen", worker.toString())
            ShowAllWorkersItem(worker = worker, modifier = Modifier.fillMaxWidth().padding(8.dp))
        }
    }
}

@Composable
fun ShowAllWorkersItem(worker: ModelAllWorkersItem, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
            Text(text = "Name: ${worker.firstName} ${worker.lastName}")
            Text(text = "Age: ${worker.age}")
            Text(text = "Address: ${worker.address}")
            Text(text = "Worker ID: ${worker.mid}")
        }
    }
}