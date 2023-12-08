package com.example.smartharvest.data.responses

import com.google.gson.annotations.SerializedName

data class ProductCatalogResponse(

	@field:SerializedName("data")
	val data: List<ProductCatalogItem>,

	@field:SerializedName("message")
	val message: String
)

data class ProductCatalogItem(

	@field:SerializedName("photoUrl")
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
	val id: Int,

	@field:SerializedName("category")
	val category: String
)
