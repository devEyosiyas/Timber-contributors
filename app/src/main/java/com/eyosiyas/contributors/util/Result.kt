package com.eyosiyas.contributors.util

/* "A Result is either a Success or an Error."

The Success class has a data property that holds the result of the operation. The Error class has an
exception property that holds the exception that was thrown */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}