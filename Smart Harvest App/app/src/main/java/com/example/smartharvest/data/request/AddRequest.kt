package com.example.smartharvest.data.request

data class AddProductItemByProducerRequest(
    var catalogId: String,
    var desc: String,
    var price: Int,
    var harvestPlace: String,
    var harvestDate: String,
    var producerId: String,
    var producerName: String,
    var status: String
)

data class AddProductItemByDistributorRequest(
    var catalogId: String,
    var desc: String,
    var price: Int,
    var harvestPlace: String,
    var harvestDate: String,
    var distributorId: String,
    var distributorName: String,
    var status: String
)

data class AddProductItemBySellerRequest(
    var catalogId: String,
    var desc: String,
    var price: Int,
    var harvestPlace: String,
    var harvestDate: String,
    var sellerId: String,
    var sellerName: String,
    var status: String
)