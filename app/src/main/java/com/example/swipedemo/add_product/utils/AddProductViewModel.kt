package com.example.swipedemo.add_product.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipedemo.add_product.models.AddProductResponseModel
import com.example.swipedemo.utils.GenericResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddProductViewModel : ViewModel() {

    private val addProductRepository: AddProductRepository by lazy {
        AddProductRepository()
    }

    private val addProductLiveData: MutableLiveData<GenericResponse<AddProductResponseModel>> by lazy {
        MutableLiveData<GenericResponse<AddProductResponseModel>>()
    }

    fun getAddProductLiveData(): LiveData<GenericResponse<AddProductResponseModel>> =
        addProductLiveData

    fun addProduct(
        productName: RequestBody?,
        productType: RequestBody,
        productPrice: RequestBody,
        productTax: RequestBody,
        imagePart: MultipartBody.Part?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addProductRepository.addProduct(
                    productName,
                    productType,
                    productPrice,
                    productTax,
                    imagePart,
                    addProductLiveData
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}