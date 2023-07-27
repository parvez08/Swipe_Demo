package com.example.swipedemo.add_product.utils

import androidx.lifecycle.MutableLiveData
import com.example.swipedemo.add_product.models.AddProductResponseModel
import com.example.swipedemo.utils.GenericResponse
import com.example.swipedemo.utils.RestAPIClass
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddProductNetworkDataSource {

    suspend fun addProduct(
        productName: RequestBody?,
        productType: RequestBody,
        productPrice: RequestBody,
        productTax: RequestBody,
        imagePart: MultipartBody.Part?,
        data: MutableLiveData<GenericResponse<AddProductResponseModel>>
    ) {
        val response = RestAPIClass.getClient().create(AddProductAPIInterface::class.java)
            .addProduct(productName, productType, productPrice, productTax, imagePart)
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