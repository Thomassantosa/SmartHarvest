package com.example.smartharvest.data.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @field:SerializedName("error")
    val error: Boolean?,

    @field:SerializedName("message")
    val message: String?
)