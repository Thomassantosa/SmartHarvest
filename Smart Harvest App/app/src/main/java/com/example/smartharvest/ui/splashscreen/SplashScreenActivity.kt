package com.example.smartharvest.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.R
import com.example.smartharvest.databinding.ActivityMainBinding
import com.example.smartharvest.databinding.ActivityProfileBinding
import com.example.smartharvest.databinding.ActivitySplashScreenBinding
import com.example.smartharvest.helper.ViewModelFactory
import com.example.smartharvest.ui.main.MainActivity
import com.example.smartharvest.ui.profile.ProfileViewModel
import com.example.smartharvest.ui.welcomepage.WelcomePageActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupViewModel()

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
// Hide ActionBar
        supportActionBar?.hide()

        val splashTimeOut = 3000

        Thread {
            try {
                Thread.sleep(splashTimeOut.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }

}