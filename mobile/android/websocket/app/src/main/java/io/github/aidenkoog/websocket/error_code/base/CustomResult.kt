package io.github.aidenkoog.websocket.error_code.base

sealed class CustomResult<out T> {
    data class Success<out T>(val value: T) : CustomResult<T>()
    data class Failure(val exception: Exception) : CustomResult<Nothing>()
}