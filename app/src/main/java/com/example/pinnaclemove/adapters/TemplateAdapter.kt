package com.example.pinnaclemove.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pinnaclemove.R
import com.example.pinnaclemove.models.Template

class TemplateAdapter(
    private val templateList: List<Template>,
    private val context: Context,
    private val onTemplateClick: (Template) -> Unit
) : RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_template_card, parent, false)
        return TemplateViewHolder(view)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        val template = templateList[position]
        holder.templateTitle.text = template.title
        holder.templateDescription.text = template.description
        holder.templateImage.setImageResource(template.imageResource)
        holder.itemView.setOnClickListener {
            onTemplateClick(template) // handle click
        }
    }

    override fun getItemCount(): Int = templateList.size

    class TemplateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val templateImage: ImageView = itemView.findViewById(R.id.templateImage)
        val templateTitle: TextView = itemView.findViewById(R.id.templateTitle)
        val templateDescription: TextView = itemView.findViewById(R.id.templateDescription)
    }
}
