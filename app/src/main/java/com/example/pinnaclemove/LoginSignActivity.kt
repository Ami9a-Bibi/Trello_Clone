package com.example.pinnaclemove

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginSignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable Edge-to-Edge layout (if needed)
        enableEdgeToEdge()

        // Set the content view to the activity's layout
        setContentView(R.layout.activity_login_sign)

        // Set padding for system bars (status and navigation bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find buttons by their IDs
        val signInButton: Button = findViewById(R.id.sign_in_btn)
        val signUpButton: Button = findViewById(R.id.sign_up_btn)

        // Set click listener for the Sign In button
        signInButton.setOnClickListener {
            // Create an Intent to navigate to SignInActivity
            val signInIntent = Intent(this, SignInActivity::class.java)
            startActivity(signInIntent)
        }

        // Set click listener for the Sign Up button
        signUpButton.setOnClickListener {
            // Create an Intent to navigate to SignUpActivity
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }
    }
}
