package com.example.smartharvest.ui.productmanagement

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartharvest.data.api.ApiConfig
import com.example.smartharvest.data.repository.Repository
import com.example.smartharvest.data.responses.ErrorResponse
import com.example.smartharvest.data.responses.LoginResult
import com.example.smartharvest.data.responses.ProductItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductManagementViewModel(private val repository: Repository) : ViewModel() {

    private val _errorResponse = MutableLiveData<ErrorResponse>()
    val errorResponse: LiveData<ErrorResponse> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listProductItem = MutableLiveData<ProductItemResponse>()
    val listProductitem: LiveData<ProductItemResponse> = _listProductItem

    fun getUser(): LiveData<LoginResult> {
        return repository.getUser()
    }
    fun getProductActiveProducer(id : String) {
        _isLoading.value = true
        val apiService = ApiConfig().getApiService()
        val productItemResponses = apiService.getProductItemProducer(id)

        productItemResponses.enqueue(object : Callback<ProductItemResponse> {
            override fun onResponse(
                call: Call<ProductItemResponse>,
                response: Response<ProductItemResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {

                        // Filter data
                        val filteredData = responseBody.data.filter { it.status == "In Producer" }
                        _listProductItem.value = ProductItemResponse(filteredData, responseBody.message)
//                        _listProductItem.value = response.body()
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ProductItemResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getProductActiveDistributor(id : String) {
        _isLoading.value = true
        val apiService = ApiConfig().getApiService()
        val productItemResponses = apiService.getProductItemDistributor(id)

        productItemResponses.enqueue(object : Callback<ProductItemResponse> {
            override fun onResponse(
                call: Call<ProductItemResponse>,
                response: Response<ProductItemResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {

                        // Filter data
                        val filteredData = responseBody.data.filter { it.status == "In Distributor" }
                        _listProductItem.value = ProductItemResponse(filteredData, responseBody.message)
//                        _listProductItem.value = response.body()
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ProductItemResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getProductActiveSeller(id : String) {
        _isLoading.value = true
        val apiService = ApiConfig().getApiService()
        val productItemResponses = apiService.getProductItemSeller(id)

        productItemResponses.enqueue(object : Callback<ProductItemResponse> {
            override fun onResponse(
                call: Call<ProductItemResponse>,
                response: Response<ProductItemResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {

                        // Filter data
                        val filteredData = responseBody.data.filter { it.status == "In Seller" }
                        _listProductItem.value = ProductItemResponse(filteredData, responseBody.message)
//                        _listProductItem.value = response.body()
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ProductItemResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}