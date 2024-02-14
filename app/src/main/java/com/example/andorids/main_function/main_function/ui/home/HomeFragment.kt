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
import com.example.andorids.main_function.main_function.ui.home.view_pager.ViewPagerAdapter
import com.example.andorids.main_function.main_function.ui.write_manager.WriteManagerActivity

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


        initPager()
        intent = Intent(requireContext(), WriteManagerActivity::class.java)
        binding.fabMain.setOnClickListener {
            fabStatuse = onFABs(fabStatuse)
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
        _binding = null
    }

    fun onFABs(isStatuse :Boolean): Boolean {
        if (isStatuse == false){
            ObjectAnimator.ofFloat(binding.fabSub1, "translationY", -200f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabSub2, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 0f,315f).apply { duration = 700 }.start()
        }
        else{
            ObjectAnimator.ofFloat(binding.fabSub1, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabSub2, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 315f,0f).apply { duration = 700 }.start()

        }
        return !isStatuse
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