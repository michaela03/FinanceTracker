package com.example.mobileappsproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            // Initialize SharedPreferences
            sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

            // Initialize views
            etUsername = findViewById(R.id.et_username)
            etPassword = findViewById(R.id.et_password)
            btnLogin = findViewById(R.id.btn_login)
            btnRegister = findViewById(R.id.btn_register)

            // Set up login button action
            btnLogin.setOnClickListener {
                val username = etUsername.text.toString().trim()
                val password = etPassword.text.toString().trim()

                // Retrieve saved username and password from SharedPreferences
                val savedUsername = sharedPreferences.getString("username", null)
                val savedPassword = sharedPreferences.getString("password", null)

                // Check if entered credentials match saved credentials
                if (username == savedUsername && password == savedPassword) {
                    // Open MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Show error message
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }

            // Set up register button action
            btnRegister.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }

        }
    }

