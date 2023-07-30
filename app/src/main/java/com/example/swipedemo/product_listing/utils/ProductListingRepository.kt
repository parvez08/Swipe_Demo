package com.example.swipedemo.product_listing.utils

import androidx.lifecycle.MutableLiveData
import com.example.swipedemo.product_listing.models.ProductListingResponseModelItem
import com.example.swipedemo.utils.GenericResponse

class ProductListingRepository {

    private var productListingNetworkDataSource: ProductListingNetworkDataSource = ProductListingNetworkDataSource()

    suspend fun fetchProducts(
        data: MutableLiveData<GenericResponse<List<ProductListingResponseModelItem>>>
    ) = productListingNetworkDataSource.fetchProducts(data)


}