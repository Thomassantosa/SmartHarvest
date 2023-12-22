package com.example.smartharvest.ui.qr

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartharvest.data.api.ApiConfig
import com.example.smartharvest.data.repository.Repository
import com.example.smartharvest.data.responses.ErrorResponse
import com.example.smartharvest.data.responses.LoginResult
import com.example.smartharvest.data.responses.OneProductItemResponse
import com.example.smartharvest.data.responses.ProductItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QRViewModel(private val repository: Repository) : ViewModel() {

    private val _errorResponse = MutableLiveData<ErrorResponse>()
    val errorResponse: LiveData<ErrorResponse> = _errorResponse

    private val _productItem = MutableLiveData<OneProductItemResponse>()
    val productitem: LiveData<OneProductItemResponse> = _productItem

    fun getUser(): LiveData<LoginResult> {
        return repository.getUser()
    }

    fun getProductItem(id : String) {
        val apiService = ApiConfig().getApiService()
        val productItemResponses = apiService.getProductItem(id)

        productItemResponses.enqueue(object : Callback<OneProductItemResponse> {
            override fun onResponse(
                call: Call<OneProductItemResponse>,
                response: Response<OneProductItemResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _productItem.value = response.body()
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<OneProductItemResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}