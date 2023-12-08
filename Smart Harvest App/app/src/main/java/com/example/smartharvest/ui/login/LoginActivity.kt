package com.example.smartharvest.ui.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.ui.main.MainActivity
import com.example.smartharvest.data.request.LoginRequest
import com.example.smartharvest.data.responses.LoginResult
import com.example.smartharvest.databinding.ActivityLoginBinding
import com.example.smartharvest.helper.ViewModelFactory
import com.example.smartharvest.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
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

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[LoginViewModel::class.java]

        loginViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setupAction() {
        binding.txtGotoregister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                val loginRequest = LoginRequest(email, password)
                loginViewModel.loginUser(loginRequest)

                loginViewModel.loginResponse.observe(this) { loginResponse ->
                    Toast.makeText(this, loginResponse.message, Toast.LENGTH_SHORT).show()

                    val login = LoginResult(
                        loginResponse.loginResult.name,
                        loginResponse.loginResult.id,
                        loginResponse.loginResult.token,
                        loginResponse.loginResult.type)
                    loginViewModel.saveSession(login)

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }

                loginViewModel.errorResponse.observe(this) { errorResponse ->
                    if(errorResponse.message != null){
                        Toast.makeText(this, errorResponse.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}