package com.example.swipedemo.product_listing.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipedemo.product_listing.models.ProductListingResponseModelItem
import com.example.swipedemo.utils.GenericResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListingViewModel : ViewModel() {

    private val productListingRepository: ProductListingRepository by lazy {
        ProductListingRepository()
    }

    private val productsLiveData: MutableLiveData<GenericResponse<List<ProductListingResponseModelItem>>> by lazy {
        MutableLiveData<GenericResponse<List<ProductListingResponseModelItem>>>()
    }

    fun getProductsLiveData(): LiveData<GenericResponse<List<ProductListingResponseModelItem>>> =
        productsLiveData

    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                productListingRepository.fetchProducts(productsLiveData)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}