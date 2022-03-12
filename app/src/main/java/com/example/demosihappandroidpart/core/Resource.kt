package com.example.demosihappandroidpart.core

sealed class Resource<T>(val data: T? = null, val message: String? = null ){
    class Loading<T>() : Resource<T>()
    class Success<T>(data: T?): Resource<T>(data = data)
    class Error<T>(data: T? = null, message: String): Resource<T>(data = data, message = message)
}
