package com.example.demosihappandroidpart.presentation

import android.app.Application
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demosihappandroidpart.core.Constants
import com.example.demosihappandroidpart.domain.repository.SihRepo
import com.example.demosihappandroidpart.domain.use_cases.use_cases_get_all_workers.UseCaseGetAllWorkers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllWorkersUseCase: UseCaseGetAllWorkers,
    private val repo: SihRepo,
    private val myApplication: Application
) : ViewModel() {
    init {
        viewModelScope.launch {
            getAllWorkers()
        }
    }

    fun getAllWorkers() {
        viewModelScope.launch {
            getAllWorkersUseCase().onEach { res ->
                Log.d(Constants.VIEWMODEL_TAG, res.toString())
            }.launchIn(viewModelScope)
        }
    }

    fun postData() {
        viewModelScope.launch {
            val stream = myApplication.resources.assets.open("eminem.jpg")
            val image = BitmapFactory.decodeStream(stream)
            repo.postImage(image)
        }
    }
}