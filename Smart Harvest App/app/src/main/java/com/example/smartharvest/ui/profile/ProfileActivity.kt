package com.example.smartharvest.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartharvest.R
import com.example.smartharvest.databinding.ActivityLoginBinding
import com.example.smartharvest.databinding.ActivityProfileBinding
import com.example.smartharvest.ui.login.LoginViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileViewModel.getUser().observe(this) {user ->
            if (user.token.isNotEmpty()) {
                binding.nameProfile.text = user.name
                binding.typeProfile.text = user.type
            }
        }

    }
}