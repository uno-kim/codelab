package com.unokim.codelab.observable


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.sql.Timestamp

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private var items: MutableList<Item> = mutableListOf()
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        adapter = MainAdapter()
        view.recycler_view.adapter = adapter
        view.recycler_view.layoutManager = LinearLayoutManager(requireContext())



        view.button.setOnClickListener {
            val item = Item(view.input.text.toString(), Timestamp(System.currentTimeMillis()))
            items.add(item)
            Log.d("uno@", "item = ${item.text}, item size = ${items.size}")
            adapter.submitList(items.toList())
        }

        return view
    }


}
