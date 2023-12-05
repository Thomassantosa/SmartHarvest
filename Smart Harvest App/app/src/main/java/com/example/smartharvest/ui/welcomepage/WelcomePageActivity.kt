package com.example.smartharvest.ui.welcomepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.smartharvest.R
import com.example.smartharvest.ui.home.HomeFragment
import com.example.smartharvest.ui.login.LoginActivity
import com.example.smartharvest.ui.register.RegisterActivity

class WelcomePageActivity : AppCompatActivity() , View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val btnMoveLoginActivity: Button = findViewById(R.id.loginawalButton)
        val btnMoveRegisterActivity: Button = findViewById(R.id.registerawalButton)

        btnMoveLoginActivity.setOnClickListener(this)
        btnMoveRegisterActivity.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.loginawalButton-> {
                startActivity(Intent(this@WelcomePageActivity, LoginActivity::class.java))
            }
            R.id.registerawalButton -> {
                startActivity(Intent(this@WelcomePageActivity, RegisterActivity::class.java))
            }
        }
    }
}