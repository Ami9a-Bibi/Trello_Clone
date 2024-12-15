package com.example.pinnaclemove.models

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pinnaclemove.R
import java.text.SimpleDateFormat
import android.widget.Toast
import java.util.*

class CardListFragment : Fragment() {

    private lateinit var cardListLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_card_list, container, false)
        cardListLayout = view.findViewById(R.id.cardListLayout)
        return view
    }
    fun addCard(card: Card) {
        // Create a LinearLayout for the card
        val cardView = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 16) // Add margin between cards
            }
            background = requireContext().getDrawable(R.drawable.rounded_card_background) // Apply rounded corners
            elevation = 4f // Optional shadow effect for API 21+
        }

        // Create and add TextViews for card details with padding/margin
        val titleTextView = TextView(requireContext()).apply {
            text = card.name
            textSize = 18f
            setTypeface(typeface, android.graphics.Typeface.BOLD) // Set bold text style
            gravity = android.view.Gravity.CENTER // Center text horizontally within the TextView
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.CENTER // Center the TextView within its parent layout
            }
        }


        val createdByTextView = TextView(requireContext()).apply {
            text = "Created by: ${card.createdBy}"
            setPadding(17, 8, 8, 8) // Add padding around the text
        }

        val assignedUserTextView = TextView(requireContext()).apply {
            text = "Assigned to: ${card.assignedTo.joinToString(", ")}"
            setPadding(17, 8, 8, 8) // Add padding around the text
        }

        val dueDateTextView = TextView(requireContext()).apply {
            text = "Due Date: ${formatDueDate(card.dueDate)}"
            setPadding(17, 8, 8, 8) // Add padding around the text
        }

        // Set the background color based on the user's selection or the card's labelColor
        cardView.setBackgroundColor(getColorByName(card.labelColor))

        // Add TextViews to the card layout
        cardView.addView(titleTextView)
        cardView.addView(createdByTextView)
        cardView.addView(assignedUserTextView)
        cardView.addView(dueDateTextView)

        // Add the Edit and Delete buttons to the card layout
        val buttonLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        val editButton = Button(requireContext()).apply {
            text = "Edit"
            layoutParams = LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f
            ).apply {
                setMargins(8, 0, 8, 0)
            }

            // Set the onClickListener for the Edit button
            setOnClickListener {
                // Code to handle the edit action (e.g., open a dialog to edit card details)
                // Example:
                Toast.makeText(requireContext(), "Edit card: ${card.name}", Toast.LENGTH_SHORT).show()
                // You can implement a dialog or navigate to a card edit screen here
                editCard(card)
            }
        }

        val deleteButton = Button(requireContext()).apply {
            text = "Delete"
            layoutParams = LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f
            ).apply {
                setMargins(8, 0, 8, 0)
            }

            // Set the onClickListener for the Delete button
            setOnClickListener {
                // Remove the card's view from the list
                Toast.makeText(requireContext(), "Deleted card: ${card.name}", Toast.LENGTH_SHORT).show()
                deleteCard(cardView) // Pass the cardView (which is the layout for the card) to deleteCard
            }
        }






        buttonLayout.addView(editButton)
        buttonLayout.addView(deleteButton)

        cardView.addView(buttonLayout) // Add button layout to the card

        // Add the card view to the card list layout
        cardListLayout.addView(cardView)
    }



    private fun formatDueDate(dueDate: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return dateFormat.format(Date(dueDate))
    }

    private fun getColorByName(colorName: String): Int {
        return when (colorName) {
            "Gray" -> 0xFF808080.toInt() // Gray
            "Light Blue" -> 0xFFADD8E6.toInt() // Light Blue
            "Light Green" -> 0xFF90EE90.toInt() // Light Green
            "Light Pink" -> 0xFFFFB6C1.toInt() // Light Pink
            "Light Yellow" -> 0xFFFFEFD5.toInt() // Light Yellow
            "Light Coral" -> 0xFFF08080.toInt() // Light Coral
            "Light Goldenrod" -> 0xFFFAFAD2.toInt() // Light Goldenrod
            else -> 0xFFFFFFFF.toInt() // Default white
        }
    }

    private fun editCard(card: Card) {
        // Add your logic for editing the card
        // You can open a dialog or another activity for editing the card details
    }

    private fun deleteCard(cardView: View) {
        // Remove the card view from the layout
        cardListLayout.removeView(cardView)
    }
}
