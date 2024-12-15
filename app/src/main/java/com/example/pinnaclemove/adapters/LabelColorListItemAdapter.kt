package com.example.pinnaclemove.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pinnaclemove.databinding.ItemLabelColorBinding

class LabelColorListItemAdapter(
    private val context: Context,
    private var list: ArrayList<String>,
    private val mSelectedColor: String
) : RecyclerView.Adapter<LabelColorListItemAdapter.MyViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLabelColorBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]

        holder.viewMain.setBackgroundColor(Color.parseColor(item))
        holder.selectedColorIv.visibility = if (item == mSelectedColor) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(position, item)
        }
    }

    interface OnItemClickListener {
        fun onClick(position: Int, color: String)
    }

    inner class MyViewHolder(binding: ItemLabelColorBinding) : RecyclerView.ViewHolder(binding.root) {
        val viewMain = binding.viewMain
        val selectedColorIv = binding.selectedColorIv
    }
}
