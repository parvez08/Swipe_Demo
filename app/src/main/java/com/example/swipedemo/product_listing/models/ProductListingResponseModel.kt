package com.example.swipedemo.product_listing.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProductListingResponseModel(

	@field:SerializedName("ProductListingResponseModel")
	val productListingResponseModel: ArrayList<ProductListingResponseModelItem?>? = null
) : Parcelable

@Parcelize
data class ProductListingResponseModelItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("product_type")
	val productType: String? = null,

	@field:SerializedName("price")
	val price: Double? = null,

	@field:SerializedName("tax")
	val tax: Double? = null,

	@field:SerializedName("product_name")
	val productName: String? = null
) : Parcelable
