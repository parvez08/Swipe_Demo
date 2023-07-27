package com.example.swipedemo.product_listing.utils

import androidx.lifecycle.MutableLiveData
import com.example.swipedemo.product_listing.models.ProductListingResponseModel
import com.example.swipedemo.product_listing.models.ProductListingResponseModelItem
import com.example.swipedemo.utils.GenericResponse
import com.example.swipedemo.utils.RestAPIClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListingNetworkDataSource {

    suspend fun fetchProducts(
        data: MutableLiveData<GenericResponse<List<ProductListingResponseModelItem>>>
    ) {
        val response = RestAPIClass.getClient().create(ProductListingAPIInterface::class.java)
            .getProducts()
        try {
            if (response.isSuccessful) data.postValue(
                GenericResponse(
                    response.body(),
                    true,
                    null
                )
            ) else data.postValue(
                GenericResponse(
                    null,
                    false,
                    response.message()
                )
            )
        } catch (e: Exception) {
            data.postValue(
                GenericResponse(
                    null,
                    false,
                    e.message
                )
            )
        }
    }
}

