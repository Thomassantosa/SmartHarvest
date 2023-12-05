package com.example.smartharvest.data.responses

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("error")
	val error: String,

	@field:SerializedName("message")
	val message: String
)
