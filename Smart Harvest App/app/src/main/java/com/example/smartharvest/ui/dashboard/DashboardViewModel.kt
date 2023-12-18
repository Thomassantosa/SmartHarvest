package com.example.smartharvest.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartharvest.data.repository.Repository
import com.example.smartharvest.data.responses.LoginResult

class DashboardViewModel(private val repository: Repository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getUser(): LiveData<LoginResult> {
        return repository.getUser()
    }
}