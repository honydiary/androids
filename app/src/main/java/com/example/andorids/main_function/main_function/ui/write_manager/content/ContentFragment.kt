package com.example.andorids.main_function.main_function.ui.write_manager.content

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.andorids.R
import com.example.andorids.databinding.FragmentContentBinding
import com.example.andorids.main_function.main_function.ui.write_manager.refact.RefactViewModle

class ContentFragment : Fragment() {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    val TAG = "ContentFragment"
    private val viewModel by activityViewModels<RefactViewModle>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d(TAG,"where?")

        _binding = FragmentContentBinding.inflate(inflater, container, false)
        binding.dayText.text = "fdskfsdf"
        val toolbar = binding.toolBar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        toolbar.title = "나중에 서버에서 값 받아~"
//        // 툴바의 왼쪽에 뒤로가기 버튼 추가
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowCustomEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        setHasOptionsMenu(true)

//        // 툴바의 오른쪽에 아이콘 버튼 추가
        toolbar.inflateMenu(R.menu.app_bar_more)
        toolbar.setOnMenuItemClickListener {item ->
            when (item.itemId) {
                R.id.appber_more -> {
                    Log.d(TAG, "why")
                    initPop(toolbar.findViewById(R.id.appber_more))
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
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "state : destroy")
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_more, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initPop(view:View){
        val pop = PopupMenu(requireContext(), view)
        pop.menuInflater.inflate(R.menu.diary_list_pop_up_menu, pop.menu)
        pop.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.mark -> {
                    Log.d(TAG, "mark")
                    return@setOnMenuItemClickListener true
                }
                R.id.refact -> {
                    Log.d(TAG, "refact")
                    viewModel.position
                    return@setOnMenuItemClickListener true
                }
                R.id.delete -> {
                    Log.d(TAG, "이건 서버에 알잘딱으로~")
                    return@setOnMenuItemClickListener true
                }
                else -> false
            }
        }
        pop.show()
    }
}
