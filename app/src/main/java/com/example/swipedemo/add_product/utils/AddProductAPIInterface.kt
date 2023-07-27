package com.example.swipedemo.add_product.utils

import com.example.swipedemo.add_product.models.AddProductResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface AddProductAPIInterface {
    @Multipart
    @POST("add")
    suspend fun addProduct(
        @Part("product_name") productName: RequestBody?,
        @Part("product_type") productType: RequestBody,
        @Part("price") price: RequestBody,
        @Part("tax") tax: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<AddProductResponseModel>
}