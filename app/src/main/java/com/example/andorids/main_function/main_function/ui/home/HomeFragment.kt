package com.example.andorids.main_function.ui.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.andorids.databinding.FragmentHomeBinding
import com.example.andorids.main_function.main_function.ui.DTO.TypeDTO
import com.example.andorids.main_function.main_function.ui.diary.adapter.AllAdapter
import com.example.andorids.main_function.main_function.ui.diary.adapter.test
import com.example.andorids.main_function.main_function.ui.home.view_pager.ViewPagerAdapter
import com.example.andorids.main_function.main_function.ui.write_manager.WriteManagerActivity
import javax.security.auth.callback.Callback

class HomeFragment : Fragment() {

    private lateinit var goodSentenceAdapter : ViewPagerAdapter
    private var _binding: FragmentHomeBinding? = null
    var fabStatuse = false
    val goodSentences = mutableListOf<String>("1", "2", "3")
//    val writeVewModel by activityViewModels<WriteViewModel>()
    lateinit var intent: Intent

    private val binding get() = _binding!!
    val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val displayMetrics = resources.displayMetrics
        val height = displayMetrics.heightPixels

        Log.d(TAG, "${height}")


        initPager()
        intent = Intent(requireContext(), WriteManagerActivity::class.java)
        binding.fabMain.setOnClickListener {
            fabStatuse = onFABs(fabStatuse, height)
        }
        binding.fabSub1.setOnClickListener {
//            writeVewModel.receiveType.value = "recode"
            intent.putExtra("inputType", "recode")
            startActivity(intent)
        }
        binding.fabSub2.setOnClickListener {
//            writeVewModel.receiveType.value ="write"
            intent.putExtra("inputType", "write")
            startActivity(intent)
//            findNavController().navigate(R.id.action_homeFragment_to_writeDiaryFragment)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fabStatuse = false
        Log.d(TAG, "state : destroy")
        _binding = null
    }

    fun onFABs(isStatate :Boolean, height : Int): Boolean {
        Log.d(TAG, "state : $isStatate")
        if (isStatate == false){
            ObjectAnimator.ofFloat(binding.fabSub1, "translationY", -(height/13).toFloat()).apply { start() }
            ObjectAnimator.ofFloat(binding.fabSub2, "translationY", -(height/7).toFloat()).apply { start() }
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 0f,315f).apply { duration = 700 }.start()
        }
        else{
            ObjectAnimator.ofFloat(binding.fabSub1, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabSub2, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 315f,0f).apply { duration = 700 }.start()

        }
        return !isStatate
    }

    fun initPager(){
        goodSentenceAdapter = ViewPagerAdapter()
        goodSentenceAdapter.goodSentences = goodSentences
        goodSentenceAdapter.notifyItemRemoved(0)
        Log.d(TAG,"$goodSentenceAdapter")
        with(binding) {
            goodSentencePager.adapter = goodSentenceAdapter
            goodSentencePager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
            ciIndicator.setViewPager(goodSentencePager)
        }
    }

}