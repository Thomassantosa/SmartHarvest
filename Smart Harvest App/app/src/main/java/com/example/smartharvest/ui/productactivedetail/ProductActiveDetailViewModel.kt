package com.example.smartharvest.ui.productactivedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.smartharvest.data.repository.Repository
import com.example.smartharvest.data.responses.LoginResult

class ProductActiveDetailViewModel(private val repository: Repository) : ViewModel() {

    fun getUser(): LiveData<LoginResult> {
        return repository.getUser()
    }
}