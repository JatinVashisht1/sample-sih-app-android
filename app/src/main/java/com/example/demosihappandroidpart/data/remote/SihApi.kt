package com.example.demosihappandroidpart.data.remote

import com.example.demosihappandroidpart.data.dto.dto_all_workers.DtoAllWorkers
import com.example.demosihappandroidpart.data.dto.dto_post_response.DtoPostResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SihApi {
    @GET("person/all")
    suspend fun getAllWorkers() : DtoAllWorkers

    @POST("upload")
    @Multipart
    suspend fun postData(
        @Part firstName: MultipartBody.Part = MultipartBody.Part.createFormData("firstName", "Priyansh",),
        @Part  lastName: MultipartBody.Part = MultipartBody.Part.createFormData("lastName", "Singh",),
        @Part  age: MultipartBody.Part = MultipartBody.Part.createFormData("age",19.toString(),),
        @Part  mid: MultipartBody.Part = MultipartBody.Part.createFormData("mid", "india",),
        @Part address: MultipartBody.Part = MultipartBody.Part.createFormData("address", "{\"houseNumber\": 13, \"street\": \"mystreet\", \"village\": \"somevillage\"}",),
        @Part image: MultipartBody.Part
    ) : Response<DtoPostResponse>
}