package com.example.demosihappandroidpart.data.dto.dto_all_workers


import com.example.demosihappandroidpart.domain.model.model_all_workers.ModelAllWorkersItem
import com.google.gson.annotations.SerializedName

data class DtoAllWorkersItem(
    @SerializedName("address")
    val address: Address = Address(),
    @SerializedName("age")
    val age: Int = 0,
    @SerializedName("firstName")
    val firstName: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("lastName")
    val lastName: String = "",
    @SerializedName("mid")
    val mid: String = "",
    @SerializedName("__v")
    val v: Int = 0
)

fun DtoAllWorkersItem.toModelAllWorkersItem() : ModelAllWorkersItem = ModelAllWorkersItem(
    address = address.toModelAddress(),
    age = age,
    firstName = firstName,
    lastName = lastName,
    mid = mid
)