package com.example.smartharvest.ui.register

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.smartharvest.R
import com.example.smartharvest.ui.home.HomeFragment
import com.example.smartharvest.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnMoveLoginActivity: TextView = findViewById(R.id.txt_gotologin)
        val btnMoveHomeActivity: Button = findViewById(R.id.registerButton)

        btnMoveLoginActivity.setOnClickListener(this)
        btnMoveHomeActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txt_gotologin-> {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
            R.id.registerButton -> {
                startActivity(Intent(this@RegisterActivity, HomeFragment::class.java))
            }
        }
    }
}