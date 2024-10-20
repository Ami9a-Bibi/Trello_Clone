package com.example.pinnaclemove.models

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log  // Add this import statement
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pinnaclemove.R
import com.example.pinnaclemove.adapters.BoardItemAdapter

class BoardsFragment : Fragment() {

    private lateinit var boardItemAdapter: BoardItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_boards, container, false)

        val boardList = ArrayList<Board>() // Replace this with your actual data source
// Add some default boards
        boardList.add(Board("Board 1", "https://www.google.com/imgres?q=animation&imgurl=https%3A%2F%2Fstatic01.nyt.com%2Fimages%2F2016%2F06%2F10%2Farts%2Ftelevision%2Fcoraline-watching-recommendation-LN%2Fcoraline-watching-recommendation-superJumbo.jpg&imgrefurl=https%3A%2F%2Fwww.nytimes.com%2F2023%2F06%2F28%2Flearning%2Fthe-unlimited-possibilities-of-animation.html&docid=Zy4pbORui2IgTM&tbnid=LbmuDqQoUal3NM&vet=12ahUKEwjsn9aJmpqJAxVngP0HHSCfCr4QM3oECC8QAA..i&w=2000&h=1125&hcb=2&ved=2ahUKEwjsn9aJmpqJAxVngP0HHSCfCr4QM3oECC8QAA", "Hilaal"))
        boardList.add(Board("Board 2", " R.drawable.ic_user.toString()", "Badar"))
        boardList.add(Board("Board 3", "@drawable/ic_user", "Hannan"))
        boardItemAdapter = BoardItemAdapter(requireContext(), boardList)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_boards_list)
        recyclerView.adapter = boardItemAdapter

        // Set layout manager for the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Optionally, check if data is populated
        Log.d("BoardsFragment", "Boards List Size: ${boardList.size}") // Now works correctly

        return view
    }
}
