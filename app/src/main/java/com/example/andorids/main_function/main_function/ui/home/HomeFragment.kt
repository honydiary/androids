package com.example.andorids.main_function.ui.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.andorids.R
import com.example.andorids.databinding.FragmentHomeBinding
import com.example.andorids.main_function.main_function.ui.home.view_pager.CardAdapter
import com.example.andorids.main_function.main_function.ui.home.view_pager.CardItem
import com.example.andorids.main_function.main_function.ui.home.view_pager.CardPagerAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    var fabStatuse = false

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
        binding.fabMain.setOnClickListener {
            fabStatuse = onFABs(fabStatuse)
        }
        binding.fabSub1.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recodeDiary)
        }
        binding.fabSub2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_writeDiaryFragment)
        }

        val testCardAdapter =
            CardPagerAdapter(requireActivity().applicationContext)
        testCardAdapter.addCardItem(
            CardItem(
                "First Card",
                "First Card"
            )
        )
        testCardAdapter.addCardItem(
            CardItem(
                "Second Card",
                "Second Card"
            )
        )
        testCardAdapter.addCardItem(
            CardItem(
                "Third Card",
                "Third Card"
            )
        )

        var mLastOffset = 0f

        binding.cardViewPager.adapter = testCardAdapter
        binding.cardViewPager.offscreenPageLimit = 3
        binding.cardViewPager.currentItem = 0

        binding.cardViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val realCurrentPosition: Int
                val nextPosition: Int
                val baseElevation: Float = (binding.cardViewPager.adapter as CardPagerAdapter).getBaseElevation()
                val realOffset: Float
                val goingLeft: Boolean = mLastOffset > positionOffset

                if (goingLeft) {
                    realCurrentPosition = position + 1
                    nextPosition = position
                    realOffset = 1 - positionOffset
                } else {
                    nextPosition = position + 1
                    realCurrentPosition = position
                    realOffset = positionOffset
                }

                if (nextPosition > (binding.cardViewPager.adapter as CardPagerAdapter).count - 1
                    || realCurrentPosition > (binding.cardViewPager.adapter as CardPagerAdapter).count - 1) {
                    return
                }

                val currentCard: CardView = (binding.cardViewPager.adapter as CardPagerAdapter).getCardViewAt(realCurrentPosition)

                currentCard.scaleX = (1 + 0.1 * (1 - realOffset)).toFloat()
                currentCard.scaleY = (1 + 0.1 * (1 - realOffset)).toFloat()

                currentCard.cardElevation = baseElevation + (baseElevation
                        * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset))

                val nextCard: CardView = (binding.cardViewPager.adapter as CardPagerAdapter).getCardViewAt(nextPosition)

                nextCard.scaleX = (1 + 0.1 * realOffset).toFloat()
                nextCard.scaleY = (1 + 0.1 * realOffset).toFloat()

                nextCard.cardElevation = baseElevation + (baseElevation
                        * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * realOffset)

                mLastOffset = positionOffset
            }

            override fun onPageSelected(position: Int) {

            }
        })


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
}