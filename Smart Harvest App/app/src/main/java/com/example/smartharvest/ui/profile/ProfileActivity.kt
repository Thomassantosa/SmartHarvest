package com.example.smartharvest.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.R
import com.example.smartharvest.databinding.ActivityProductHistoryBinding
import com.example.smartharvest.databinding.ActivityProfileBinding
import com.example.smartharvest.databinding.FragmentQrBinding
import com.example.smartharvest.helper.ViewModelFactory
import com.example.smartharvest.ui.login.LoginViewModel
import com.example.smartharvest.ui.producthistory.ProductHistoryViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileViewModel.getUser().observe(this) {user ->
            if (user.token.isNotEmpty()) {
                binding.nameProfile.text = "Hello " + user.name
            }
        }

    }
}