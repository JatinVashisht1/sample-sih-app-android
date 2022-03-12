package com.example.demosihappandroidpart.data.dto.dto_all_workers


import com.example.demosihappandroidpart.domain.model.model_all_workers.ModelAddress
import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("houseNumber")
    val houseNumber: Int = 0,
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("street")
    val street: String = "",
    @SerializedName("village")
    val village: String = ""
)

fun Address.toModelAddress() : ModelAddress = ModelAddress(
    houseNumber = houseNumber,
    street = street,
    village = village
)