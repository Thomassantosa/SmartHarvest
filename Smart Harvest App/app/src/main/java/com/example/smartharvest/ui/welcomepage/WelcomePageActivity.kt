package com.example.smartharvest.ui.welcomepage

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.smartharvest.R
import com.example.smartharvest.R.id.welcomeregisterButton
import com.example.smartharvest.ui.home.HomeFragment
import com.example.smartharvest.ui.login.LoginActivity
import com.example.smartharvest.ui.register.RegisterActivity

class WelcomePageActivity : AppCompatActivity() , View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val btnMoveLoginActivity: Button = findViewById(R.id.welcomeloginButton)
        val btnMoveRegisterActivity: Button = findViewById(welcomeregisterButton)

        btnMoveLoginActivity.setOnClickListener(this)
        btnMoveRegisterActivity.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.welcomeloginButton-> {
                startActivity(Intent(this@WelcomePageActivity, LoginActivity::class.java))
            }
            R.id.welcomeregisterButton -> {
                startActivity(Intent(this@WelcomePageActivity, RegisterActivity::class.java))
            }
        }
    }
}