package com.example.pinnaclemove.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinnaclemove.R
import com.example.pinnaclemove.models.User
import com.example.pinnaclemove.utils.Constants

open class MemberItemAdapter(private val context: Context, private var list: ArrayList<User>) :
    RecyclerView.Adapter<MemberItemAdapter.MyViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_member, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = list[position]

        // Set name and email
        holder.memberNameTextView.text = model.name
        holder.memberEmailTextView.text = model.email

        // Load user image
        Glide
            .with(context)
            .load(model.image)
            .fitCenter()
            .placeholder(R.drawable.ic_user)
            .into(holder.memberImageView)

        // Set visibility of selected_member_iv based on model.selected
        if (model.selected) {
            holder.selectedMemberImageView.visibility = View.VISIBLE
        } else {
            holder.selectedMemberImageView.visibility = View.GONE
        }

        // Handle item click
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                if (model.selected) {
                    onClickListener!!.onClick(position, model, Constants.UN_SELECT)
                } else {
                    onClickListener!!.onClick(position, model, Constants.SELECT)
                }
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, user: User, action: String)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val memberNameTextView: TextView = view.findViewById(R.id.member_name_tv)
        val memberEmailTextView: TextView = view.findViewById(R.id.member_email_tv)
        val memberImageView: ImageView = view.findViewById(R.id.user_image)
        val selectedMemberImageView: ImageView = view.findViewById(R.id.selected_member_iv)
    }
}
