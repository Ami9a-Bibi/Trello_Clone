package com.example.pinnaclemove

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat

class HomePageActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var menuIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Initialize the DrawerLayout and Menu Icon
        drawerLayout = findViewById(R.id.drawer_layout)
        menuIcon = findViewById(R.id.menu_icon)

        // Handle menu icon click to open the drawer
        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)  // Open the drawer
        }
    }
}
