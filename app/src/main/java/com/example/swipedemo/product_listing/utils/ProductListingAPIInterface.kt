package com.example.swipedemo.product_listing.utils

import com.example.swipedemo.product_listing.models.ProductListingResponseModelItem
import retrofit2.Response
import retrofit2.http.GET


interface ProductListingAPIInterface {

    @GET("get")
    suspend fun getProducts(): Response<List<ProductListingResponseModelItem>>

}
