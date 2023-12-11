package com.example.smartharvest.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartharvest.data.api.ApiConfig
import com.example.smartharvest.data.repository.Repository
import com.example.smartharvest.data.responses.ErrorResponse
import com.example.smartharvest.data.responses.ProductCatalogResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _errorResponse = MutableLiveData<ErrorResponse>()
    val errorResponse: LiveData<ErrorResponse> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listProductCatalog = MutableLiveData<ProductCatalogResponse>()
    val listProductCatalog: LiveData<ProductCatalogResponse> = _listProductCatalog

    suspend fun getProductCatalog(token : String) {
        _isLoading.value = true
        val apiService = ApiConfig().getApiService()
        val productCatalogResponses = apiService.getAllProductCatalog("Bearer $token")

        productCatalogResponses.enqueue(object : Callback<ProductCatalogResponse> {
            override fun onResponse(
                call: Call<ProductCatalogResponse>,
                response: Response<ProductCatalogResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listProductCatalog.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ProductCatalogResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}