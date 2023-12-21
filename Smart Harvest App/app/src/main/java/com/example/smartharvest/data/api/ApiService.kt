package com.example.smartharvest.data.api

import com.example.smartharvest.data.request.AddProductItemByDistributorRequest
import com.example.smartharvest.data.request.AddProductItemByProducerRequest
import com.example.smartharvest.data.request.AddProductItemBySellerRequest
import com.example.smartharvest.data.request.LoginRequest
import com.example.smartharvest.data.request.RegisterRequest
import com.example.smartharvest.data.responses.LoginResponse
import com.example.smartharvest.data.responses.ProductCatalogResponse
import com.example.smartharvest.data.responses.ProductItemResponse
import com.example.smartharvest.data.responses.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("register")
    fun registerUser(
        @Body request: RegisterRequest,
    ): Call<RegisterResponse>

    @POST("login")
    fun loginUser(
        @Body request: LoginRequest,
    ): Call<LoginResponse>

    @GET("products-catalog")
        fun getAllProductCatalog(
        @Header("Authorization") token: String
    ): Call<ProductCatalogResponse>

    @GET("products-item-producer/{id}")
    fun getProductItemProducer(
        @Path("id") type: String
    ): Call<ProductItemResponse>

    @GET("products-item-distributor/{id}")
    fun getProductItemDistributor(
        @Path("id") type: String
    ): Call<ProductItemResponse>

    @GET("products-item-seller/{id}")
    fun getProductItemSeller(
        @Path("id") type: String
    ): Call<ProductItemResponse>

    @POST("product-item")
    fun uploadItemByProducer(
        @Body request: AddProductItemByProducerRequest,
    ): Call<ProductItemResponse>

    @POST("product-item")
    fun uploadItemByDistributor(
        @Body request: AddProductItemByDistributorRequest,
    ): Call<ProductItemResponse>

    @POST("product-item")
    fun uploadItemBySeller(
        @Body request: AddProductItemBySellerRequest,
    ): Call<ProductItemResponse>
}