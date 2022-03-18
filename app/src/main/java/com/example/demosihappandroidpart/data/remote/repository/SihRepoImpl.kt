package com.example.demosihappandroidpart.data.remote.repository

import android.graphics.Bitmap
import android.util.Log
import com.example.demosihappandroidpart.core.Constants
import com.example.demosihappandroidpart.data.dto.dto_all_workers.DtoAllWorkers
import com.example.demosihappandroidpart.data.remote.SihApi
import com.example.demosihappandroidpart.domain.repository.SihRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class SihRepoImpl @Inject constructor (private val api: SihApi) : SihRepo{
    override suspend fun getAllWorkers(): DtoAllWorkers {
        val data = api.getAllWorkers()
        Log.d(Constants.VIEWMODEL_TAG, "data in repo is $data")
        return data
    }

    override suspend fun postImage(
        inputStream: Bitmap,
        village: String,
        street: String,
        houseNumber: String,
        mid: String,
        age: String,
        lastName: String,
        firstName: String
    ) {
        CoroutineScope(IO).launch {
            val bos = ByteArrayOutputStream()
            inputStream.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitmapdata = bos.toByteArray()
            val request =  RequestBody.create("image/jpeg".toMediaTypeOrNull(), bitmapdata)  // read all bytes using kotlin extension
            val filePart = MultipartBody.Part.createFormData(
                "workerImg",
                "eminem.jpg",
                request
            )

            val address = "{\"houseNumber\": \"${houseNumber}\", \"street\": \"${street}\", \"village\": \"${village}\"}"

            try {
                val a = api.postData(
                    image = filePart,
                    firstName = MultipartBody.Part.createFormData("firstName", firstName.toString()),
                    lastName = MultipartBody.Part.createFormData("lastName", lastName.toString()),
                    mid = MultipartBody.Part.createFormData("mid", mid.toString()),
                    address = MultipartBody.Part.createFormData("address", address.toString())
                )
                Log.d(Constants.SIH_REPO_TAG, a.toString())
            }
            catch (e: Exception) {
                Log.d(Constants.SIH_REPO_TAG, e.toString())
            }
            Log.d("MyActivity", "on finish upload file")
        }
    }
}