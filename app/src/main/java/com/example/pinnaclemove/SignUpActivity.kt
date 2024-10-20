package com.example.pinnaclemove

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        // Ensure the view is ready before setting window insets listener
        findViewById<View>(R.id.main)?.post {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val signUpButton: Button = findViewById(R.id.sign_up_page_btn)

        // Set click listener for the Sign Up button
        signUpButton.setOnClickListener {
            // Create an Intent to navigate to HomePageActivity
            val signUpIntent = Intent(this, HomePageActivity::class.java)
            startActivity(signUpIntent)
        }
    }
}
