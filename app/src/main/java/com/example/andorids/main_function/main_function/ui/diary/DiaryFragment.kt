package com.example.andorids.main_function.ui.diary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.andorids.databinding.FragmentDiaryBinding
import com.example.andorids.main_function.main_function.ui.diary.adapter.AllAdapter
import com.example.andorids.main_function.main_function.ui.diary.adapter.DiaryItem
import com.example.andorids.main_function.main_function.ui.home.view_pager.ViewPagerAdapter

class DiaryFragment : Fragment() {
    lateinit var recyclerAdapta:AllAdapter
    val dateList = mutableListOf<DiaryItem>(DiaryItem("test","test"),DiaryItem("test","2024.12.12"))

    private var _binding: FragmentDiaryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val diaryViewModel =
            ViewModelProvider(this).get(DiaryViewModel::class.java)

        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initAll()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAll(){
        recyclerAdapta = AllAdapter(requireContext())
        recyclerAdapta.dateList = dateList
        recyclerAdapta.notifyItemRemoved(0)
        with(binding) {
            recyclerView.adapter = recyclerAdapta
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}