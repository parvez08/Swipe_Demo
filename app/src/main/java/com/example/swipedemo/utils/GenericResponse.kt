package com.example.swipedemo.utils

data class GenericResponse<T>(val data: T?, val success: Boolean, val message: String?)