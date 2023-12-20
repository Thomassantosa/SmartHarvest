package com.example.smartharvest.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartharvest.data.repository.Repository
import com.example.smartharvest.data.responses.LoginResult

class ProfileViewModel (private val repository: Repository) : ViewModel(){

    fun getUser(): LiveData<LoginResult> {
        return repository.getUser()
    }
}