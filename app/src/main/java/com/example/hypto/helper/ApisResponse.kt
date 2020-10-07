package com.example.hypto.helper

import java.lang.Exception

sealed class ApisResponse<out T> {
    data class Success<T>(var response : T) : ApisResponse<T>()
    data class Error(var exception : Exception) : ApisResponse<Nothing>()
    object LOADING : ApisResponse<Nothing>()
    object COMPLETED : ApisResponse<Nothing>()
}