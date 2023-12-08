package com.example.smartharvest.ui.qr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is QR Fragment"
    }
    val text: LiveData<String> = _text
}