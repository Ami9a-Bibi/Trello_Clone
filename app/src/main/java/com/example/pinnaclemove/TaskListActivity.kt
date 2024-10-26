package com.example.pinnaclemove

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pinnaclemove.models.Board
import com.example.pinnaclemove.models.User
import com.example.pinnaclemove.models.Task
import com.example.pinnaclemove.models.Card

import com.example.pinnaclemove.databinding.ActivityTaskListBinding
import com.example.pinnaclemove.utils.Constants

class TaskListActivity : AppCompatActivity() {

    private lateinit var mBoardDetails: Board
    private lateinit var mBoardDocumentID: String
    public lateinit var mAssignedMembersDetailList: ArrayList<User>
    private lateinit var binding: ActivityTaskListBinding  // Declare binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)  // Inflate binding
        setContentView(binding.root)  // Set the root view

        if (intent.hasExtra(Constants.DOCUMENT_ID)) {
            mBoardDocumentID = intent.getStringExtra(Constants.DOCUMENT_ID) ?: ""
        }

        showProgressDialog()
        loadBoardDetails(mBoardDocumentID)
    }

    private fun loadBoardDetails(boardDocumentID: String) {
        mBoardDetails = mockBoardData(boardDocumentID)
        hideProgressDialog()
        setActionBar()
        loadAssignedMembers()
    }

    private fun loadAssignedMembers() {
        mAssignedMembersDetailList = mockAssignedMembersList()
        setUpMembersList(mAssignedMembersDetailList)
    }

    private fun mockBoardData(boardDocumentID: String): Board {
        return Board(
            name = "Sample Board",
            image = "",
            createdBy = "Admin",
            assignedTo = arrayListOf(),
            documentID = boardDocumentID,
            taskList = mockTasks()
        )
    }

    private fun mockTasks(): ArrayList<Task> {
        return arrayListOf(
            Task(title = "Task 1", createdBy = "Admin", cards = arrayListOf()),
            Task(title = "Task 2", createdBy = "Admin", cards = arrayListOf())
        )
    }

    private fun mockAssignedMembersList(): ArrayList<User> {
        return arrayListOf(
            User("1", "John Doe", "john@example.com"),
            User("2", "Jane Smith", "jane@example.com")
        )
    }

    private fun setActionBar() {
        setSupportActionBar(binding.taskListActivityToolbar)  // Use binding to access the toolbar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_white)
            title = mBoardDetails.name
        }
        binding.taskListActivityToolbar.setNavigationOnClickListener { onBackPressed() }  // Use binding
    }

    private fun setUpMembersList(list: ArrayList<User>) {
        binding.taskListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)  // Use binding
        binding.taskListRv.setHasFixedSize(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_members, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_members -> {
                val intent = Intent(this, MembersActivity::class.java)
                intent.putExtra(Constants.BOARD_DETAIL, mBoardDetails)
                startActivityForResult(intent, MEMBERS_REQUEST_CODE)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == MEMBERS_REQUEST_CODE) {
            // Handle result from MembersActivity
        }
    }

    private fun showProgressDialog() {
        // Implement your progress dialog logic here
    }

    private fun hideProgressDialog() {
        // Implement your progress dialog hide logic here
    }

    fun addCardToTask(position: Int, cardName: String) {
        // Ensure that the task list is not empty before removing the last item
        if (mBoardDetails.taskList.isNotEmpty()) {
            mBoardDetails.taskList.removeAt(mBoardDetails.taskList.size - 1)
        }

        // Instead of getting the current user ID from Firestore, use a placeholder or pass it as a parameter
        val currentUserID = "placeholder_user_id" // Replace with actual user ID logic if necessary
        val cardAssignedUsersList: ArrayList<String> = ArrayList()
        cardAssignedUsersList.add(currentUserID) // Add current user ID to the list

        val card = Card(cardName, currentUserID, cardAssignedUsersList)

        // Get the existing card list from the selected task and add the new card
        val cardList = mBoardDetails.taskList[position].cards
        cardList.add(card) // Add the card to cards list in the task list

        // Create a new Task object with the updated cards list
        val task = Task(
            mBoardDetails.taskList[position].title,
            mBoardDetails.taskList[position].createdBy,
            cardList
        )

        // Update the task list at the specified position
        mBoardDetails.taskList[position] = task

        // Optionally show a progress dialog if needed (this may depend on your implementation)
        showProgressDialog()

        // If you have a local database or another way to persist data, update it here
        // Example: updateLocalDatabase(mBoardDetails)
    }
    fun cardDetails(taskListPosition: Int, cardPosition: Int) {
        val intent = Intent(this@TaskListActivity, CardDetailsActivity::class.java)
        intent.putExtra(Constants.BOARD_DETAIL, mBoardDetails)
        intent.putExtra(Constants.TASK_LIST_ITEM_POSITION, taskListPosition)
        intent.putExtra(Constants.CARD_LIST_ITEM_POSITION, cardPosition)
        intent.putExtra(Constants.BOARD_MEMBERS_LIST, mAssignedMembersDetailList)
        startActivityForResult(intent, CARD_DETAILS_REQUEST_CODE)
    }

    fun updateCardsInTaskList(taskListPosition: Int, cards : ArrayList<Card>){
        //remove the 'add card' card
        mBoardDetails.taskList.removeAt(mBoardDetails.taskList.size - 1)
        mBoardDetails.taskList[taskListPosition].cards = cards
        //show progress dialog and update the database with the new order of list
        showProgressDialog()
    }

    fun deleteTaskList(position : Int){
        mBoardDetails.taskList.removeAt(position)
        mBoardDetails.taskList.removeAt(mBoardDetails.taskList.size-1)

        showProgressDialog()
    }

    fun updateTaskList(position: Int, listName: String, model : Task){
        val task = Task(listName,model.createdBy)
        mBoardDetails.taskList[position] = task
        mBoardDetails.taskList.removeAt(mBoardDetails.taskList.size-1)

        showProgressDialog()
    }

    fun createTaskList(taskName: String) {
        // Placeholder for current user ID, replace with actual logic if needed
        val currentUserID = "placeholder_user_id" // Replace with actual user ID logic if necessary

        // Create a new task with the provided name and current user ID
        val task = Task(taskName, currentUserID)

        // Add the new task at the beginning of the task list
        mBoardDetails.taskList.add(0, task)

        // Remove the last position as we have added the item manually for adding the TaskList
        if (mBoardDetails.taskList.size > 1) {
            mBoardDetails.taskList.removeAt(mBoardDetails.taskList.size - 1)
        }

        // Optionally show a progress dialog if needed (this may depend on your implementation)
        showProgressDialog()

        // If you have a local database or another way to persist data, update it here
        // Example: updateLocalDatabase(mBoardDetails)
    }


    companion object {
        const val MEMBERS_REQUEST_CODE: Int = 13
        const val CARD_DETAILS_REQUEST_CODE : Int = 14

    }
}
