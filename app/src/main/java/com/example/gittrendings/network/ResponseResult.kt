package com.example.gittrendings.network

import com.squareup.moshi.JsonClass

sealed class ResponseResult<out T> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Failure(val error: ErrorResponse) : ResponseResult<Nothing>()
    object NetworkError : ResponseResult<Nothing>()
}

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val statusCode: Int = 0,
    val errorCode: Int? = 0,
    val message: String?
)