package com.example.pinnaclemove

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.pinnaclemove.databinding.ActivityCreateBoardBinding
import com.example.pinnaclemove.models.Board
import com.example.pinnaclemove.models.BoardsFragment
import java.io.InputStream

class CreateBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar with back navigation
        setSupportActionBar(binding.toolbarCreateBoardActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set image click listener
        binding.createBoardImage.setOnClickListener {
            // Open gallery to pick image
            openGallery()
        }

        // Set create board button listener
        binding.createBoardCreateBtn.setOnClickListener {
            createBoard()
        }

        // Set view boards button listener to load BoardsFragment
        binding.btnViewBoards.setOnClickListener {
            loadFragment(BoardsFragment())
        }
    }

    // Method to load the fragment
    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Method to open gallery and pick an image
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 101)
    }

    // Handle gallery result to set image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            val inputStream: InputStream? = selectedImageUri?.let { contentResolver.openInputStream(it) }
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            binding.createBoardImage.setImageBitmap(bitmap)
        }
    }

    // Method to handle create board logic (local logic for now)
    private fun createBoard() {
        val boardName = binding.createBoardNameEt.text.toString().trim()

        // Validate input
        if (boardName.isEmpty()) {
            Toast.makeText(this, "Please enter a board name", Toast.LENGTH_SHORT).show()
            return
        }

        // If valid, create Board object and display success message
        val board = Board(boardName, "User", "imageUrl") // Replace with actual logic for image URL
        Toast.makeText(this, "Board '$boardName' created successfully!", Toast.LENGTH_SHORT).show()

        // Optionally, finish activity and return to previous screen
        finish()
    }

    // Handling back navigation
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
