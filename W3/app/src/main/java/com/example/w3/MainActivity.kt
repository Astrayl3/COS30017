package com.example.w3

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText = findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.login)
        val registerButton = findViewById<Button>(R.id.register)
        val passwordTextView = findViewById<TextView>(R.id.textView2)

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                val value = it.data?.getStringExtra("Test1")
                Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            }
        }

        // Set password field visibility when clicked
        usernameEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordEditText.visibility = View.VISIBLE
                passwordTextView.visibility = View.VISIBLE
            }
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            // Handle login logic here
        }

        registerButton.setOnClickListener {
            // Handle register button click here
        }
    }
}