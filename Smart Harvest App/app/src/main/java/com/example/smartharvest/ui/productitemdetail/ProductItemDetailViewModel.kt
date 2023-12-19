package com.example.smartharvest.ui.productitemdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartharvest.data.repository.Repository
import com.example.smartharvest.data.responses.ErrorResponse
import com.example.smartharvest.data.responses.LoginResult
import com.example.smartharvest.data.responses.ProductItemResponse

class ProductItemDetailViewModel(private val repository: Repository) : ViewModel() {

    private val _errorResponse = MutableLiveData<ErrorResponse>()
    val errorResponse: LiveData<ErrorResponse> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listProductItem = MutableLiveData<ProductItemResponse>()
    val listProductitem: LiveData<ProductItemResponse> = _listProductItem

    fun getUser(): LiveData<LoginResult> {
        return repository.getUser()
    }
}