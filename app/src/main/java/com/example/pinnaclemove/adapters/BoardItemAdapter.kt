package com.example.pinnaclemove.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinnaclemove.R
import com.example.pinnaclemove.databinding.ItemBoardBinding
import com.example.pinnaclemove.models.Board

class BoardItemAdapter(
    private val context: Context,
    private var list: ArrayList<Board>
) : RecyclerView.Adapter<BoardItemAdapter.MyViewHolder>() {

    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(position: Int, model: Board)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    inner class MyViewHolder(val binding: ItemBoardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBoardBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = list[position]

        // Use Glide to load the image from a URL or drawable resource
        Glide.with(context)
            .load(model.image)  // This will load either a URL or resource ID based on the image string
            .placeholder(R.drawable.ic_user)  // Show placeholder while loading
            .error(R.drawable.ic_user)        // Show placeholder if an error occurs
            .into(holder.binding.itemBoardIv)               // Load image into ImageView

        // Set default typeface
        holder.binding.itemBoardNameTv.typeface = Typeface.DEFAULT_BOLD
        holder.binding.itemBoardCreatedByTv.typeface = Typeface.DEFAULT

        // Set text
        holder.binding.itemBoardNameTv.text = model.name
        holder.binding.itemBoardCreatedByTv.text = "Created by: ${model.createdBy}"

        // Set click listener
        holder.binding.root.setOnClickListener {
            onClickListener?.onClick(position, model)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
