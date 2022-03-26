package com.example.demosihappandroidpart.presentation.get_all_workers_screen.components

import com.example.demosihappandroidpart.domain.model.model_all_workers.ModelAllWorkers

data class AllWorkersScreenState(
    val isLoading: Boolean = true,
    val allWorkers: ModelAllWorkers = ModelAllWorkers(),
    val error: String = ""
)
