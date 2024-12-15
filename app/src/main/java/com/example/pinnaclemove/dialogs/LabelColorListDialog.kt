package com.example.pinnaclemove.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pinnaclemove.R
import com.example.pinnaclemove.adapters.LabelColorListItemAdapter
import com.example.pinnaclemove.databinding.DialogListBinding

abstract class LabelColorListDialog(
    context: Context,
    private var list: ArrayList<String>,
    private val title: String = "",
    private val mSelectedColor: String = ""
) : Dialog(context) {

    private var adapter: LabelColorListItemAdapter? = null
    private lateinit var binding: DialogListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCanceledOnTouchOutside(true)
        setCancelable(true)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.dialogTitleTv.text = title

        binding.dialogListRv.layoutManager = LinearLayoutManager(context)
        adapter = LabelColorListItemAdapter(context, list, mSelectedColor)
        binding.dialogListRv.adapter = adapter

        adapter!!.onItemClickListener = object : LabelColorListItemAdapter.OnItemClickListener {
            override fun onClick(position: Int, color: String) {
                dismiss()
                onItemSelected(color)
            }
        }
    }

    protected abstract fun onItemSelected(color: String)
}
