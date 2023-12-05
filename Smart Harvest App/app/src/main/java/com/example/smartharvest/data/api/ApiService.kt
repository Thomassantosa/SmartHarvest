package com.example.smartharvest.data.api

import com.example.smartharvest.data.request.LoginRequest
import com.example.smartharvest.data.request.RegisterRequest
import com.example.smartharvest.data.responses.LoginResponse
import com.example.smartharvest.data.responses.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    fun registerUser(
        @Body request: RegisterRequest,
    ): Call<RegisterResponse>

    @POST("login")
    fun loginUser(
        @Body request: LoginRequest,
    ): Call<LoginResponse>
}