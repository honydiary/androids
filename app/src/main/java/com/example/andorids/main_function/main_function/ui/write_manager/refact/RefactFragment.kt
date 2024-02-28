package com.example.andorids.main_function.main_function.ui.write_manager.refact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.andorids.R
import com.example.andorids.databinding.FragmentContentBinding

class RefactFragment : Fragment() {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    val TAG = "RefactFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        val toolBar = binding.toolBar
        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowCustomEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        setHasOptionsMenu(true)
        toolBar.inflateMenu(R.menu.app_bar_more)
        toolBar.setOnMenuItemClickListener {item ->
            when (item.itemId) {
                R.id.appber_more -> {
                    Log.d(TAG, "why")
                    true
                }
                else -> false
            }
        }
        binding.toolBar.setNavigationOnClickListener {
            requireActivity().finish()
        }
        return binding.root
    }

}