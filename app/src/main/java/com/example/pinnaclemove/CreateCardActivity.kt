package com.example.pinnaclemove

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pinnaclemove.models.Card
import com.example.pinnaclemove.models.CardListFragment
import java.text.SimpleDateFormat
import java.util.*

class CreateCardActivity : AppCompatActivity() {

    private lateinit var userSelectionGroup: RadioGroup
    private lateinit var userSpinner: Spinner
    private lateinit var newUserEditText: EditText
    private lateinit var cardTitle: EditText
    private lateinit var createdBy: EditText
    private lateinit var dueDateTextView: TextView
    private lateinit var pickDueDateButton: Button
    private lateinit var pickTimeButton: Button
    private lateinit var createCardButton: Button
    private lateinit var colorSpinner: Spinner
    private lateinit var cardListFragment: CardListFragment
    private var selectedDueDate: Long = 0
    private var selectedDueTime: Long = 0

    // Default users to be displayed in the Spinner
    private val defaultUsers = listOf("User 1", "User 2", "User 3") // Add your default user names here

    // Available color options for the Spinner
    private val colorOptions = listOf(
        "Gray", "Light Blue", "Light Green", "Light Pink",
        "Light Yellow", "Light Coral", "Light Goldenrod"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        // Initialize views
        userSelectionGroup = findViewById(R.id.userSelectionGroup)
        userSpinner = findViewById(R.id.userSpinner)
        newUserEditText = findViewById(R.id.newUserEditText)
        cardTitle = findViewById(R.id.cardTitle)
        createdBy = findViewById(R.id.createdBy)
        dueDateTextView = findViewById(R.id.dueDateTextView)
        pickDueDateButton = findViewById(R.id.pickDueDateButton)
        pickTimeButton = findViewById(R.id.pickTimeButton)
        createCardButton = findViewById(R.id.createCardButton)
        colorSpinner = findViewById(R.id.colorSpinner)

        // Set up user spinner with default users
        val userAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, defaultUsers)
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userSpinner.adapter = userAdapter

        // Set up color spinner
        val colorAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorOptions)
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSpinner.adapter = colorAdapter

        // Initialize fragment
        cardListFragment = CardListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, cardListFragment)
            .commit()

        // Set up listeners for user selection (radio buttons)
        userSelectionGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.selectUserRadioButton -> {
                    userSpinner.visibility = View.VISIBLE
                    newUserEditText.visibility = View.GONE
                }
                R.id.enterNewUserRadioButton -> {
                    userSpinner.visibility = View.GONE
                    newUserEditText.visibility = View.VISIBLE
                }
            }
        }

        // Set up date and time pickers
        pickDueDateButton.setOnClickListener {
            showDatePicker()
        }

        pickTimeButton.setOnClickListener {
            showTimePicker()
        }

        createCardButton.setOnClickListener {
            createCard()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            selectedDueDate = selectedDate.timeInMillis
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dueDateTextView.text = dateFormat.format(selectedDate.time)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute ->
            val selectedTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hourOfDay)
                set(Calendar.MINUTE, minute)
            }
            selectedDueTime = selectedTime.timeInMillis
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            dueDateTextView.text = "${dueDateTextView.text} ${timeFormat.format(selectedTime.time)}"
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)

        timePickerDialog.show()
    }

    private fun createCard() {
        val name = cardTitle.text.toString()
        val createdByName = createdBy.text.toString()
        val assignedTo = ArrayList<String>().apply {
            if (userSelectionGroup.checkedRadioButtonId == R.id.selectUserRadioButton) {
                // Get the selected user from Spinner and add it to the list
                add(userSpinner.selectedItem.toString())
            } else {
                // Get the new user name from EditText and add it to the list
                add(newUserEditText.text.toString())
            }
        }
        val labelColor = colorSpinner.selectedItem.toString()

        // Combine date and time for the final dueDate value
        val dueDate = selectedDueDate + (selectedDueTime % (24 * 60 * 60 * 1000))

        // Create a new Card object
        val card = Card(
            name = name,
            createdBy = createdByName,
            assignedTo = assignedTo,
            labelColor = labelColor,
            dueDate = dueDate
        )

        // Add the card to the CardListFragment
        cardListFragment.addCard(card)

        // Clear the input fields
        clearFields()
    }

    private fun clearFields() {
        cardTitle.text.clear()
        createdBy.text.clear()
        newUserEditText.text.clear()
        dueDateTextView.text = "Due Date"
        selectedDueDate = 0
        selectedDueTime = 0
    }
}
