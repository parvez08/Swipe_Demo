package com.example.swipedemo.product_listing.utils

import androidx.lifecycle.MutableLiveData
import com.example.swipedemo.product_listing.models.ProductListingResponseModelItem
import com.example.swipedemo.utils.GenericResponse
import retrofit2.Retrofit
import org.koin.java.KoinJavaComponent.inject


class ProductListingNetworkDataSource {

    private val restApi: Retrofit by inject(Retrofit::class.java)

    suspend fun fetchProducts(
        data: MutableLiveData<GenericResponse<List<ProductListingResponseModelItem>>>
    ) {
        val response = restApi.create(ProductListingAPIInterface::class.java)
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

