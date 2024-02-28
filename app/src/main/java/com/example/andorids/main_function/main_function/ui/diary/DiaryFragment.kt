package com.example.andorids.main_function.ui.diary

import android.content.Intent
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
import com.example.andorids.main_function.main_function.ui.diary.adapter.test
import com.example.andorids.main_function.main_function.ui.home.view_pager.ViewPagerAdapter
import com.example.andorids.main_function.main_function.ui.write_manager.WriteManagerActivity

class DiaryFragment : Fragment(), test {
    val TAG = "DiaryFragment"
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
        initAll(0, "init")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAll(position: Int, state: String){
        recyclerAdapta = AllAdapter(requireContext(), this)
        recyclerAdapta.dateList = dateList
        when (state) {
            "mark" -> {
                Log.d(TAG, "마크")
            }
            "refact" -> {
                Log.d(TAG, "화면 넘기기")
                transformActivity(position, state)
            }
            "delete" -> {
                dateList.removeAt(position)
            }
            "view" -> {
                transformActivity(position, state)
            }
        }
        recyclerAdapta.notifyItemRemoved(0)
        with(binding) {
            recyclerView.adapter = recyclerAdapta
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun transformActivity(position: Int, state: String){
        val intent = Intent(requireContext(), WriteManagerActivity::class.java)
        intent.putExtra("inputType", state)
        intent.putExtra("inputPosition", position)
        startActivity(intent)

    }

    override fun call(position: Int, state: String) {
        Log.d(TAG, "$position, $state")
        initAll(position, state)
    }
}