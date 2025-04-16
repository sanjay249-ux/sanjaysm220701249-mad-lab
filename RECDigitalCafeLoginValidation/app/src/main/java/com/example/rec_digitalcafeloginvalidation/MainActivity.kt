package com.example.rec_digitalcafeloginvalidation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val btLogin: Button = findViewById(R.id.btLogin)
        val btClear: Button = findViewById(R.id.btClear)

        btLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Condition 1: Both fields should not be empty
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Condition 2: Email should be a valid college email (example@college.edu)
            val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.edu.in$")
            if (!email.matches(emailRegex)) {
                Toast.makeText(this, "Invalid College Email ID", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Condition 3: Password must have 1 letter, 1 number, 1 special character, and be at least 8 characters long
            val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")
            if (!password.matches(passwordRegex)) {
                Toast.makeText(
                    this,
                    "Password must be at least 8 characters, contain 1 letter, 1 number, and 1 special character",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            // If all validations pass, move to next activity
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        btClear.setOnClickListener {
            etEmail.text.clear()
            etPassword.text.clear()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}