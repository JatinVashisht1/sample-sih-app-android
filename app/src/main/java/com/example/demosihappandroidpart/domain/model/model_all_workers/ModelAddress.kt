package com.example.demosihappandroidpart.domain.model.model_all_workers

data class ModelAddress(
    val houseNumber: Int = 0,
    val street: String = "",
    val village: String = ""
){
    override fun toString(): String {
        return "House Number: ${houseNumber}, Street: ${street}, village: $village"
    }
}
