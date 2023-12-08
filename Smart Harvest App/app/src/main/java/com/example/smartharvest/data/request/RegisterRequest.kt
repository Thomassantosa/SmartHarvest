package com.example.smartharvest.data.request

data class RegisterRequest(
    var name: String,
    var email: String,
    var password: String,
    var type: String
)