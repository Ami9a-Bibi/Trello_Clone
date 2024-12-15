package com.example.pinnaclemove

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_help)

        // Handle window insets for edge-to-edge support
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Handle click events for FAQ questions
        val questionAddMember = findViewById<TextView>(R.id.question_add_member)
        val answerAddMember = findViewById<TextView>(R.id.answer_add_member)

        questionAddMember.setOnClickListener {
            // Toggle visibility of the answer
            if (answerAddMember.visibility == View.GONE) {
                answerAddMember.visibility = View.VISIBLE
            } else {
                answerAddMember.visibility = View.GONE
            }
        }

        val questionDeleteBoard = findViewById<TextView>(R.id.question_delete_board)
        val answerDeleteBoard = findViewById<TextView>(R.id.answer_delete_board)

        questionDeleteBoard.setOnClickListener {
            // Toggle visibility of the answer
            if (answerDeleteBoard.visibility == View.GONE) {
                answerDeleteBoard.visibility = View.VISIBLE
            } else {
                answerDeleteBoard.visibility = View.GONE
            }
        }

        val questionDueDate = findViewById<TextView>(R.id.question_due_date)
        val answerDueDate = findViewById<TextView>(R.id.answer_due_date)

        questionDueDate.setOnClickListener {
            // Toggle visibility of the answer
            if (answerDueDate.visibility == View.GONE) {
                answerDueDate.visibility = View.VISIBLE
            } else {
                answerDueDate.visibility = View.GONE
            }
        }

        val questionChangeAccountInfo = findViewById<TextView>(R.id.question_change_account_info)
        val answerChangeAccountInfo = findViewById<TextView>(R.id.answer_change_account_info)

        questionChangeAccountInfo.setOnClickListener {
            // Toggle visibility of the answer
            if (answerChangeAccountInfo.visibility == View.GONE) {
                answerChangeAccountInfo.visibility = View.VISIBLE
            } else {
                answerChangeAccountInfo.visibility = View.GONE
            }
        }

        val questionViewCards = findViewById<TextView>(R.id.question_view_cards)
        val answerViewCards = findViewById<TextView>(R.id.answer_view_cards)

        questionViewCards.setOnClickListener {
            // Toggle visibility of the answer
            if (answerViewCards.visibility == View.GONE) {
                answerViewCards.visibility = View.VISIBLE
            } else {
                answerViewCards.visibility = View.GONE
            }
        }

        val questionAddBoard = findViewById<TextView>(R.id.question_add_board)
        val answerAddBoard = findViewById<TextView>(R.id.answer_add_board)

        questionAddBoard.setOnClickListener {
            // Toggle visibility of the answer
            if (answerAddBoard.visibility == View.GONE) {
                answerAddBoard.visibility = View.VISIBLE
            } else {
                answerAddBoard.visibility = View.GONE
            }
        }
        val questionAddChecklist = findViewById<TextView>(R.id.question_add_checklist)
        val answerAddChecklist = findViewById<TextView>(R.id.answer_add_checklist)

        questionAddChecklist.setOnClickListener {
            // Toggle visibility of the answer
            if (answerAddChecklist.visibility == View.GONE) {
                answerAddChecklist.visibility = View.VISIBLE
            } else {
                answerAddChecklist.visibility = View.GONE
            }
        }


    }
}