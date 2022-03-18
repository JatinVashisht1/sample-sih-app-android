package com.example.demosihappandroidpart.domain.repository

import android.graphics.Bitmap
import com.example.demosihappandroidpart.data.dto.dto_all_workers.DtoAllWorkers

interface SihRepo {
    suspend fun getAllWorkers() : DtoAllWorkers
    suspend fun postImage(
        inputStream: Bitmap,
        village: String,
        street: String,
        houseNumber: String,
        mid: String,
        age: String,
        lastName: String,
        firstName: String
    )
}