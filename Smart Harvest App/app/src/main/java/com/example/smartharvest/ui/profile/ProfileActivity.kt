package com.example.smartharvest.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.R
import com.example.smartharvest.databinding.ActivityLoginBinding
import com.example.smartharvest.databinding.ActivityProfileBinding
import com.example.smartharvest.helper.ViewModelFactory
import com.example.smartharvest.ui.login.LoginViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
    }

    private fun setupViewModel() {
        profileViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[ProfileViewModel::class.java]

        profileViewModel.getUser().observe(this) { user ->
            user?.let {
                if (it.token.isNotEmpty()) {
                    binding.nameProfile.text = it.name
                    binding.typeProfile.text = it.type
                }
            }
        }
    }
}