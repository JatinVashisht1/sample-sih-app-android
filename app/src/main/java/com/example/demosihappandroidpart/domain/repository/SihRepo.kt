package com.example.demosihappandroidpart.domain.repository

import android.graphics.Bitmap
import com.example.demosihappandroidpart.data.dto.dto_all_workers.DtoAllWorkers

interface SihRepo {
    suspend fun getAllWorkers() : DtoAllWorkers
    suspend fun postImage(inputStream: Bitmap)
}