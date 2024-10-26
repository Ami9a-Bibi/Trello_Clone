package com.example.pinnaclemove.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinnaclemove.R
import com.example.pinnaclemove.models.SelectedMembers

open class CardMemberListItemAdapter(
    private val context: Context,
    private var list: ArrayList<SelectedMembers>,
    private val assignedMembers: Boolean
) : RecyclerView.Adapter<CardMemberListItemAdapter.MyViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_card_selected_member, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = list[position]

        if (position == list.size - 1 && assignedMembers) {
            holder.addMemberImageView.visibility = View.VISIBLE
            holder.selectedMemberImageView.visibility = View.GONE
        } else {
            holder.addMemberImageView.visibility = View.GONE
            holder.selectedMemberImageView.visibility = View.VISIBLE

            // Set user's image
            Glide
                .with(context)
                .load(model.image)
                .fitCenter()
                .placeholder(R.drawable.ic_user)
                .into(holder.selectedMemberImageView)
        }

        holder.itemView.setOnClickListener {
            onClickListener?.onClick()
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val selectedMemberImageView: ImageView = view.findViewById(R.id.selected_member_image_iv)
        val addMemberImageView: ImageView = view.findViewById(R.id.add_member_iv)
    }
}
