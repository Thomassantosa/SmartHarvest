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

interface TestApiService {

    @GET("stories")
    suspend fun getStories(
        @Header("Authorization") token: String
    ): ProductCatalogResponse
}