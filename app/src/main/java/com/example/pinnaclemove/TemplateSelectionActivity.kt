package com.example.pinnaclemove

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pinnaclemove.adapters.TemplateAdapter
import com.example.pinnaclemove.models.Template

class TemplateSelectionActivity : AppCompatActivity() {
    private lateinit var recyclerViewTemplates: RecyclerView
    private lateinit var templateAdapter: TemplateAdapter
    private lateinit var templateList: MutableList<Template>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_selection)

        // Setup Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        // Setup RecyclerView
        recyclerViewTemplates = findViewById(R.id.recyclerViewTemplates)
        recyclerViewTemplates.layoutManager = LinearLayoutManager(this)

        // Dummy data for templates
        templateList = mutableListOf(
            Template("Basic Board", "Made by Trello", R.drawable.img_1),
            Template("Project Management", "Good for teams", R.drawable.img_4),
            Template("Kanban Board", "Made by Trello", R.drawable.img_1),
            Template("Agile Board Template", "Good for teams", R.drawable.img_4),
            Template("Basic Board", "Made by Trello", R.drawable.img_1),
            Template("Remote Team Hub", "Good for teams", R.drawable.img_4),
            Template("Basic Board", "Made by Trello", R.drawable.img_1),
            Template("Project Management", "Good for teams", R.drawable.img_4)
        )

        // Set adapter with click listener
        templateAdapter = TemplateAdapter(templateList, this) { template ->
            // Redirect to CreateBoardActivity with the selected template data
            val intent = Intent(this, CreateBoardActivity::class.java)
            intent.putExtra("TEMPLATE_TITLE", template.title)
            startActivity(intent)
        }
        recyclerViewTemplates.adapter = templateAdapter
    }
}
