package com.example.smartharvest.ui.addproductitem

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartharvest.data.api.ApiConfig
import com.example.smartharvest.data.repository.Repository
import com.example.smartharvest.data.request.AddProductItemByDistributorRequest
import com.example.smartharvest.data.request.AddProductItemByProducerRequest
import com.example.smartharvest.data.request.AddProductItemBySellerRequest
import com.example.smartharvest.data.responses.ErrorResponse
import com.example.smartharvest.data.responses.LoginResult
import com.example.smartharvest.data.responses.ProductCatalogResponse
import com.example.smartharvest.data.responses.ProductItemResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductItemViewModel(private val repository: Repository) : ViewModel()  {

    private val _errorResponse = MutableLiveData<ErrorResponse>()
    val errorResponse: LiveData<ErrorResponse> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listProductCatalog = MutableLiveData<ProductCatalogResponse>()
    val listProductCatalog: LiveData<ProductCatalogResponse> = _listProductCatalog

    private val _addProductResponse = MutableLiveData<ProductItemResponse>()
    val addProductResponse: LiveData<ProductItemResponse> = _addProductResponse

    fun getUser(): LiveData<LoginResult> {
        return repository.getUser()
    }

    fun getAllProductCatalog(token : String) {
        Log.d("TEST C", "TEST MASUK")
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
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ProductCatalogResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun uploadItemByProducer(catalogId: String, desc: String, price: Int,
                             harvestPlace: String, harvestDate: String,
                             producerId: String, producerName: String, status: String){
        _isLoading.value = true
        val apiService = ApiConfig().getApiService()
        val uploadItemRequest = apiService.uploadItemByProducer(
            AddProductItemByProducerRequest(catalogId,
            desc,price,
            harvestPlace,
            harvestDate, producerId, producerName, status)
        )

        uploadItemRequest.enqueue(object : Callback<ProductItemResponse> {
            override fun onResponse(
                call: Call<ProductItemResponse>,
                response: Response<ProductItemResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _addProductResponse.value = response.body()
                    }
                } else {
                    val errBody = response.errorBody()
                    val errJsonString = errBody?.string()
                    val gson = Gson()
                    _errorResponse.value = gson.fromJson(errJsonString, ErrorResponse::class.java)
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                _errorResponse.value = ErrorResponse(null, null)
            }

            override fun onFailure(call: Call<ProductItemResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun uploadItemByDistributor(catalogId: String, desc: String, price: Int,
                             harvestPlace: String, harvestDate: String,
                             distributorId: String, distributorName: String, status: String){
        _isLoading.value = true
        val apiService = ApiConfig().getApiService()
        val uploadItemRequest = apiService.uploadItemByDistributor(
            AddProductItemByDistributorRequest(catalogId,
                desc,price,
                harvestPlace,
                harvestDate, distributorId, distributorName, status)
        )

        uploadItemRequest.enqueue(object : Callback<ProductItemResponse> {
            override fun onResponse(
                call: Call<ProductItemResponse>,
                response: Response<ProductItemResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _addProductResponse.value = response.body()
                    }
                } else {
                    val errBody = response.errorBody()
                    val errJsonString = errBody?.string()
                    val gson = Gson()
                    _errorResponse.value = gson.fromJson(errJsonString, ErrorResponse::class.java)
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                _errorResponse.value = ErrorResponse(null, null)
            }

            override fun onFailure(call: Call<ProductItemResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun uploadItemBySeller(catalogId: String, desc: String, price: Int,
                             harvestPlace: String, harvestDate: String,
                             sellerId: String, sellerName: String, status: String){
        _isLoading.value = true
        val apiService = ApiConfig().getApiService()
        val uploadItemRequest = apiService.uploadItemBySeller(
            AddProductItemBySellerRequest(catalogId,
                desc,price,
                harvestPlace,
                harvestDate, sellerId, sellerName, status)
        )

        uploadItemRequest.enqueue(object : Callback<ProductItemResponse> {
            override fun onResponse(
                call: Call<ProductItemResponse>,
                response: Response<ProductItemResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _addProductResponse.value = response.body()
                    }
                } else {
                    val errBody = response.errorBody()
                    val errJsonString = errBody?.string()
                    val gson = Gson()
                    _errorResponse.value = gson.fromJson(errJsonString, ErrorResponse::class.java)
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                _errorResponse.value = ErrorResponse(null, null)
            }

            override fun onFailure(call: Call<ProductItemResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}