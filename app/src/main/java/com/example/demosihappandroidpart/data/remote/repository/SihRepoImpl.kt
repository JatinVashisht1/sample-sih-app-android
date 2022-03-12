package com.example.demosihappandroidpart.data.remote.repository

import android.util.Log
import com.example.demosihappandroidpart.core.Constants
import com.example.demosihappandroidpart.data.dto.dto_all_workers.DtoAllWorkers
import com.example.demosihappandroidpart.data.remote.SihApi
import com.example.demosihappandroidpart.domain.repository.SihRepo
import javax.inject.Inject

class SihRepoImpl @Inject constructor (private val api: SihApi) : SihRepo{
    override suspend fun getAllWorkers(): DtoAllWorkers {
        val data = api.getAllWorkers()
        Log.d(Constants.VIEWMODEL_TAG, "data in repo is $data")
        return data
    }
}