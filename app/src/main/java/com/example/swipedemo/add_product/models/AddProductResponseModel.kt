package com.example.swipedemo.add_product.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class AddProductResponseModel(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("product_details")
	val productDetails: ProductDetails? = null
) : Parcelable

@Parcelize
data class ProductDetails(

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
