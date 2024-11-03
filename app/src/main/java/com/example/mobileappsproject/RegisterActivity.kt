package com.example.mobileappsproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etRegisterUsername: EditText
    private lateinit var etRegisterPassword: EditText
    private lateinit var btnRegisterConfirm: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize views
        etRegisterUsername = findViewById(R.id.et_register_username)
        etRegisterPassword = findViewById(R.id.et_register_password)
        btnRegisterConfirm = findViewById(R.id.btn_register_confirm)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Register button action
        btnRegisterConfirm.setOnClickListener {
            val username = etRegisterUsername.text.toString().trim()
            val password = etRegisterPassword.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Save user credentials to SharedPreferences
                sharedPreferences.edit().apply {
                    putString("username", username)
                    putString("password", password)
                    apply()
                }

                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()

                // Navigate back to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
