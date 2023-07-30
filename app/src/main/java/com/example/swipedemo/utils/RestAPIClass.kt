package com.example.swipedemo.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestAPIClass {
    private lateinit var retrofit: Retrofit
    private val baseUrl = "https://app.getswipe.in/api/public/"

    fun getClient(): Retrofit {
        retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}