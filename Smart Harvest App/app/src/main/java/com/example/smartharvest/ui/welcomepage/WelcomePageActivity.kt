package com.example.smartharvest.ui.welcomepage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.smartharvest.R
import com.example.smartharvest.R.id.welcomeregisterButton
import com.example.smartharvest.data.request.LoginRequest
import com.example.smartharvest.data.responses.LoginResult
import com.example.smartharvest.databinding.ActivityMainBinding
import com.example.smartharvest.databinding.ActivityWelcomePageBinding
import com.example.smartharvest.ui.home.HomeFragment
import com.example.smartharvest.ui.login.LoginActivity
import com.example.smartharvest.ui.main.MainActivity
import com.example.smartharvest.ui.register.RegisterActivity

class WelcomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupView()
        setupAction()

    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.welcomeloginButton.setOnClickListener {
            startActivity(Intent(this@WelcomePageActivity, LoginActivity::class.java))
        }
        binding.welcomeregisterButton.setOnClickListener {
            startActivity(Intent(this@WelcomePageActivity, RegisterActivity::class.java))
        }
    }
}