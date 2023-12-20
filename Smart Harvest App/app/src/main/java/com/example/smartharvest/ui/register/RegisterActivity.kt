package com.example.smartharvest.ui.register

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.data.request.RegisterRequest
import com.example.smartharvest.databinding.ActivityRegisterBinding
import com.example.smartharvest.helper.ViewModelFactory
import com.example.smartharvest.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
        registerViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[RegisterViewModel::class.java]

        registerViewModel.isLoading.observe(this) {
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
        binding.txtGotologin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
        binding.registerButton.setOnClickListener {
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmpassword.text.toString()
            val type = binding.typespinner.selectedItem.toString()

            if (email.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            }else if (password.isEmpty()){
                Toast.makeText(this@RegisterActivity, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            }else if (name.isEmpty()){
                Toast.makeText(this@RegisterActivity, "Name cannot be empty", Toast.LENGTH_SHORT).show()
            }else if (confirmPassword.isEmpty()){
                Toast.makeText(this@RegisterActivity, "Confirmation Password cannot be empty", Toast.LENGTH_SHORT).show()
            }else if (confirmPassword != password){
                Toast.makeText(this@RegisterActivity, "Incorrect confirmation password", Toast.LENGTH_SHORT).show()
            } else {
                val registerRequest = RegisterRequest(name, email, password, type)
                registerViewModel.registerUser(registerRequest)

                registerViewModel.registerResponse.observe(this) { response ->
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }

                registerViewModel.errorResponse.observe(this) { response ->
                    if(response.message != null){
                        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}