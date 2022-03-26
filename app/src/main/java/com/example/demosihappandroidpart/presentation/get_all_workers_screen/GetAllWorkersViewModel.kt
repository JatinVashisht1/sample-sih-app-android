package com.example.demosihappandroidpart.presentation.get_all_workers_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demosihappandroidpart.core.Resource
import com.example.demosihappandroidpart.domain.model.model_all_workers.ModelAllWorkers
import com.example.demosihappandroidpart.domain.use_cases.use_cases_get_all_workers.UseCaseGetAllWorkers
import com.example.demosihappandroidpart.presentation.get_all_workers_screen.components.AllWorkersScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetAllWorkersViewModel @Inject constructor(
    private val getAllWorkersUseCase: UseCaseGetAllWorkers
) : ViewModel() {
    private val allWorkersStateLiveData = MutableLiveData<AllWorkersScreenState>(
        AllWorkersScreenState()
    )
    val allWorkersState = allWorkersStateLiveData

    init {
            getAllWorkers()
    }

    fun getAllWorkers() {
        viewModelScope.launch {
            getAllWorkersUseCase().collectLatest { latestValue ->
                when (latestValue) {
                    is Resource.Error -> allWorkersStateLiveData.value = AllWorkersScreenState(
                        isLoading = false,
                        error = latestValue.message ?: "Unable to reach server"
                    )
                    is Resource.Loading -> allWorkersStateLiveData.value =
                        AllWorkersScreenState(isLoading = true)
                    is Resource.Success -> allWorkersStateLiveData.value = AllWorkersScreenState(
                        isLoading = false,
                        allWorkers = latestValue.data ?: ModelAllWorkers()
                    )
                }
            }
        }
    }
}