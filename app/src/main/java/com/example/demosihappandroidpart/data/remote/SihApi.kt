package com.example.demosihappandroidpart.data.remote

import com.example.demosihappandroidpart.data.dto.dto_all_workers.DtoAllWorkers
import retrofit2.http.GET

interface SihApi {
    @GET("person/all")
    suspend fun getAllWorkers() : DtoAllWorkers
}