package com.example.pinnaclemove

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomePageActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var menuIcon: ImageView
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Initialize the DrawerLayout, Menu Icon, and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        menuIcon = findViewById(R.id.menu_icon)
        navigationView = findViewById(R.id.navigator_view)

        // Handle menu icon click to open the drawer
        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)  // Open the drawer
        }

        // Handle navigation item selection and navigate to respective activities
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
        //    R.id.nav_boards -> {
                    // Navigate to BoardsActivity
           //         val intent = Intent(this, BoardsActivity::class.java)
         //           startActivity(intent)
         //       }
               R.id.nav_workspaces -> {
                    // Navigate to WorkspacesActivity
               val intent = Intent(this, MyWorkSpaceActivity::class.java)
                   startActivity(intent)
               }
                R.id.nav_my_cards -> {
                    // Navigate to MyCardsActivity
                    val intent = Intent(this, CreateCardActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_offline_boards -> {
                    // Navigate to OfflineBoardsActivity
                    val intent = Intent(this, CreateBoardActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_settings -> {
                    // Navigate to SettingsActivity
                    val intent = Intent(this, SettingActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_help -> {
                    // Navigate to HelpActivity
                    val intent = Intent(this, HelpActivity::class.java)
                    startActivity(intent)
                }
            }
            // Close the drawer when an item is clicked
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)  // Close drawer if open
        } else {
            super.onBackPressed()
        }
    }
}
