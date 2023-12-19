package com.example.smartharvest.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.budiyev.android.codescanner.CodeScanner
import com.example.smartharvest.R
import com.example.smartharvest.data.responses.LoginResponse
import com.example.smartharvest.data.responses.LoginResult
import com.example.smartharvest.databinding.ActivityProfileBinding
import com.example.smartharvest.databinding.FragmentQrBinding

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

    }
}