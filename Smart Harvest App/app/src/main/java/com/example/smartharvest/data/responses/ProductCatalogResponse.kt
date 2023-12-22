package com.example.smartharvest.data.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductCatalogResponse(

	@field:SerializedName("productCatalog")
	val productcatalog: List<ProductcatalogItem>,

	@field:SerializedName("message")
	val message: String
)

@Parcelize
data class ProductcatalogItem(

	@field:SerializedName("photo_url")
	val photoUrl: String,

	@field:SerializedName("national_price")
	val nationalPrice: Int,

	@field:SerializedName("prediction_price")
	val predictionPrice: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("category")
	val category: String
): Parcelable
