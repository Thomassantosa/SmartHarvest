package com.example.smartharvest.data.api

import com.example.smartharvest.data.request.LoginRequest
import com.example.smartharvest.data.request.RegisterRequest
import com.example.smartharvest.data.responses.LoginResponse
import com.example.smartharvest.data.responses.ProductCatalogResponse
import com.example.smartharvest.data.responses.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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
}