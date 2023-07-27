package com.example.swipedemo.add_product.utils

import androidx.lifecycle.MutableLiveData
import com.example.swipedemo.add_product.models.AddProductResponseModel
import com.example.swipedemo.utils.GenericResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddProductRepository {

    private var addProductNetworkDataSource: AddProductNetworkDataSource =
        AddProductNetworkDataSource()

    suspend fun addProduct(
        productName: RequestBody?,
        productType: RequestBody,
        productPrice: RequestBody,
        productTax: RequestBody,
        imagePart: MultipartBody.Part?,
        data: MutableLiveData<GenericResponse<AddProductResponseModel>>
    ) = addProductNetworkDataSource.addProduct(productName,productType,productPrice, productTax, imagePart, data)
}