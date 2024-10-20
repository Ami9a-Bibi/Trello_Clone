package com.example.pinnaclemove

import android.content.Intent
import android.os.Bundle
import android.util.Log  // Added for debugging
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pinnaclemove.ui.theme.PinnacleMoveTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content for the splash screen
        setContent {
            PinnacleMoveTheme {
                SplashScreenContent {
                    Log.d("SplashActivity", "Navigating to LoginSignActivity")  // Debugging log
                    // Navigate to LoginSignActivity once the splash is complete
                    startActivity(Intent(this, LoginSignActivity::class.java))
                    finish()  // Close SplashActivity
                }
            }
        }
    }
}

@Composable
fun SplashScreenContent(onTimeout: () -> Unit) {
    // Use LaunchedEffect to trigger the delay and navigation
    LaunchedEffect(key1 = true) {
        delay(2000)  // 2 seconds delay
        Log.d("SplashScreenContent", "Splash timeout complete")  // Debugging log
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0052CC))  // Set background color to blue
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Display the logo image
        Image(
            painter = painterResource(id = R.drawable.logo),  // Replace with your logo file name
            contentDescription = "App Logo"
        )

        // Optionally, show a loading indicator or animation
        CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    PinnacleMoveTheme {
        SplashScreenContent {}
    }
}
