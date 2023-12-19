package com.example.smartharvest.data.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductItemResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String? = null
)

@Parcelize
data class DataItem(

	@field:SerializedName("productcatalog_id")
	val productcatalogId: String? = null,

	@field:SerializedName("price_distributor")
	val priceDistributor: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("national_price")
	val nationalPrice: Int? = null,

	@field:SerializedName("prediction_price")
	val predictionPrice: Int? = null,

	@field:SerializedName("price_producer")
	val priceProducer: Int? = null,

	@field:SerializedName("sell_date")
	val sellDate: String? = null,

	@field:SerializedName("seller_name")
	val sellerName: String? = null,

	@field:SerializedName("harvest_date")
	val harvestDate: String? = null,

	@field:SerializedName("distributor_id")
	val distributorId: String? = null,

	@field:SerializedName("producer_name")
	val producerName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("producer_id")
	val producerId: String? = null,

	@field:SerializedName("price_seller")
	val priceSeller: Int? = null,

	@field:SerializedName("distributor_name")
	val distributorName: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("photo_url")
	val photoUrl: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("seller_id")
	val sellerId: String? = null,

	@field:SerializedName("harvest_place")
	val harvestPlace: String? = null,

	@field:SerializedName("status")
	val status: String? = null
): Parcelable
