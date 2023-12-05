package com.example.smartharvest.ui.register

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.smartharvest.MainActivity
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
//                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                val emailEditText: EditText = findViewById(R.id.email)
                val passwordEditText: EditText = findViewById(R.id.password)
                val nameEditText: EditText = findViewById(R.id.name)
                val confirmpasswordEditText: EditText = findViewById(R.id.confirmpassword)

                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val name = nameEditText.text.toString()
                val confrimpassword = confirmpasswordEditText.text.toString()

                if (email.isEmpty()) {
                    Toast.makeText(this@RegisterActivity, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }else if (password.isEmpty()){
                    Toast.makeText(this@RegisterActivity, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }else if (name.isEmpty()){
                    Toast.makeText(this@RegisterActivity, "Name tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }else if (confrimpassword.isEmpty()){
                    Toast.makeText(this@RegisterActivity, "Confirmation Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }else if (confrimpassword != password){
                    Toast.makeText(this@RegisterActivity, "Confirmation Password tidak sama dengan password", Toast.LENGTH_SHORT).show()
                } else {
                    val moveDataIntent = Intent(this@RegisterActivity, MainActivity::class.java)
                    moveDataIntent.putExtra(MainActivity.EXTRA_EMAIL, email)
                    moveDataIntent.putExtra(MainActivity.EXTRA_PASSWORD, password)
                    startActivity(moveDataIntent)
                }
            }
        }
    }
}