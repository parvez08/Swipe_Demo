package com.example.swipedemo.add_product.utils

import androidx.lifecycle.MutableLiveData
import com.example.swipedemo.add_product.models.AddProductResponseModel
import com.example.swipedemo.utils.GenericResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import org.koin.java.KoinJavaComponent.inject

class AddProductNetworkDataSource {
    private val restApi: Retrofit by inject(Retrofit::class.java)
    suspend fun addProduct(
        productName: RequestBody?,
        productType: RequestBody,
        productPrice: RequestBody,
        productTax: RequestBody,
        imagePart: MultipartBody.Part?,
        data: MutableLiveData<GenericResponse<AddProductResponseModel>>
    ) {
        val response = restApi.create(AddProductAPIInterface::class.java)
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