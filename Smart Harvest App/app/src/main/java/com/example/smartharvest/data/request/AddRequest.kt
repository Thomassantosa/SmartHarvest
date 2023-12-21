package com.example.smartharvest.data.request

data class AddProductItemByProducerRequest(
    var productcatalog_id: String,
    var description: String,
    var price_producer: Int,
    var harvest_place: String,
    var harvest_date: String,
    var producer_id: String,
    var producer_name: String,
    var status: String
)

data class AddProductItemByDistributorRequest(
    var productcatalog_id: String,
    var description: String,
    var price_distributor: Int,
    var harvest_place: String,
    var harvest_date: String,
    var distributor_id: String,
    var distributor_name: String,
    var status: String
)

data class AddProductItemBySellerRequest(
    var productcatalog_id: String,
    var description: String,
    var price_seller: Int,
    var harvest_place: String,
    var harvest_date: String,
    var seller_id: String,
    var seller_name: String,
    var status: String
)