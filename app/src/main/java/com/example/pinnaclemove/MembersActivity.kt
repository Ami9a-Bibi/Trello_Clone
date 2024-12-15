package com.example.pinnaclemove

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pinnaclemove.adapters.MemberItemAdapter
import com.example.pinnaclemove.models.Board
import com.example.pinnaclemove.models.User
import com.example.pinnaclemove.utils.Constants
import com.example.pinnaclemove.databinding.ActivityMembersBinding
import com.example.pinnaclemove.databinding.DialogSerachMemberBinding

class MembersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMembersBinding
    private lateinit var mBoardDetails : Board
    private lateinit var mAssignedMembersList : ArrayList<User>
    private var anyChangesMade : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityMembersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra(Constants.BOARD_DETAIL)){
            mBoardDetails = intent.getParcelableExtra<Board>(Constants.BOARD_DETAIL)!!
            setUpMembersList(mockAssignedMembersList())  // Mock data for testing
        }
        setActionBar()
    }

    private fun setUpMembersList(list: ArrayList<User>){
        mAssignedMembersList = list

        // Initialize the RecyclerView and set its adapter using the view binding reference
        binding.membersListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.membersListRv.setHasFixedSize(true)

        val adapter = MemberItemAdapter(this, list)
        binding.membersListRv.adapter = adapter
    }

    private fun setActionBar(){
        setSupportActionBar(binding.membersActivityToolbar)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
            actionBar.title = "${mBoardDetails.name} Members"
        }
        binding.membersActivityToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_member, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add_member ->{
                dialogSearchMember()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dialogSearchMember(){
        val dialog = Dialog(this)
        val dialogBinding = DialogSerachMemberBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.addTv.setOnClickListener {
            val email = dialogBinding.emailSearchMemberEt.text.toString()
            if(email.isNotEmpty()){
                // Replace Firebase with mock data handling
                val user = mockUserByEmail(email)
                if (user != null) {
                    memberDetails(user)
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter email address", Toast.LENGTH_SHORT).show()
            }
        }

        dialogBinding.cancelTv.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun mockUserByEmail(email: String): User? {
        // Mock finding a user by email
        return if (email == "john@example.com") {
            User("1", "John Doe", email)
        } else {
            null
        }
    }

    private fun mockAssignedMembersList(): ArrayList<User> {
        // Return a mock list of assigned members for testing
        val list = ArrayList<User>()
        list.add(User("1", "John Doe", "john@example.com"))
        list.add(User("2", "Jane Smith", "jane@example.com"))
        return list
    }

    private fun memberDetails(user: User) {
        mBoardDetails.assignedTo.add(user.id)
        mAssignedMembersList.add(user)
        setUpMembersList(mAssignedMembersList)
    }

    override fun onBackPressed() {
        if(anyChangesMade){
            setResult(Activity.RESULT_OK)
        }
        super.onBackPressed()
    }
}
