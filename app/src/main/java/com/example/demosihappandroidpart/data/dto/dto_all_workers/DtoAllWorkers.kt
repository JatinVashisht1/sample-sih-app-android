package com.example.demosihappandroidpart.data.dto.dto_all_workers


import com.example.demosihappandroidpart.domain.model.model_all_workers.ModelAllWorkers
import com.google.gson.annotations.SerializedName

class DtoAllWorkers : ArrayList<DtoAllWorkersItem>()

fun DtoAllWorkers.toModelAllWorkers() : ModelAllWorkers = ModelAllWorkers(
    allWorkers = this.toList().map {
        it.toModelAllWorkersItem()
    }
)