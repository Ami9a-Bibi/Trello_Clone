package com.example.pinnaclemove.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pinnaclemove.databinding.DialogListBinding  // View Binding import
import com.example.pinnaclemove.adapters.MemberItemAdapter
import com.example.pinnaclemove.models.User

abstract class MembersListDialog(
    context: Context,
    private var list: ArrayList<User>,
    private val title: String = ""
) : Dialog(context) {

    private var adapter: MemberItemAdapter? = null
    private lateinit var binding: DialogListBinding  // ViewBinding instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = DialogListBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.dialogTitleTv.text = title

        if (list.isNotEmpty()) {
            binding.dialogListRv.layoutManager = LinearLayoutManager(context)
            adapter = MemberItemAdapter(context, list)
            binding.dialogListRv.adapter = adapter
            adapter!!.setOnClickListener(object : MemberItemAdapter.OnClickListener {
                override fun onClick(position: Int, user: User, action: String) {
                    dismiss()
                    onItemSelected(user, action)
                }
            })
        }
    }

    protected abstract fun onItemSelected(user: User, action: String)
}
