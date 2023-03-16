package com.example.rendering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rendering.promotion.EndorsingAdapter
import com.example.rendering.promotion.dimensions

class EcommerceFragment : Fragment() {

    private val mainAdapter = EndorsingAdapter({ dimension ->
        Toast.makeText(requireContext(), "$dimension", Toast.LENGTH_SHORT).show()
    }, { endorsing ->
        Toast.makeText(requireContext(), "$endorsing", Toast.LENGTH_SHORT).show()
    })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ecommerce, container, false).apply {

            findViewById<RecyclerView>(R.id.rclContent).apply {
                adapter = mainAdapter
                mainAdapter.sharedPool = recycledViewPool
            }
        }
    }

    fun refresh() {
        mainAdapter.submitList(dimensions)
    }
}