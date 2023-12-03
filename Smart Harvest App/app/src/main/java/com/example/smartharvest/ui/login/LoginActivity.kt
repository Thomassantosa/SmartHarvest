package com.example.smartharvest.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.smartharvest.R
import com.example.smartharvest.ui.home.HomeFragment
import com.example.smartharvest.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() , View.OnClickListener {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnMoveRegisterActivity: TextView = findViewById(R.id.txt_gotoregister)
        val btnMoveHomeActivity: Button = findViewById(R.id.loginButton)

        btnMoveRegisterActivity.setOnClickListener(this)
        btnMoveHomeActivity.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txt_gotoregister-> {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
            R.id.loginButton -> {
                startActivity(Intent(this@LoginActivity, HomeFragment::class.java))
            }
        }
    }


}