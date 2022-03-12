package com.example.demosihappandroidpart.domain.model.model_all_workers

data class ModelAllWorkersItem(
    val address: ModelAddress = ModelAddress(),
    val age: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val mid: String = "",
)
