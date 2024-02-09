package com.example.andorids.main_function.main_function.ui.home.view_pager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.example.andorids.databinding.MainCardAdapterBinding
import com.example.andorids.main_function.main_function.ui.home.view_pager.CardAdapter.Companion.MAX_ELEVATION_FACTOR

class CardPagerAdapter(val context:Context): PagerAdapter(), CardAdapter{

    private var mViews: MutableList<CardView> = mutableListOf()
    private var mData: MutableList<CardItem> = mutableListOf()
    private lateinit var binding : MainCardAdapterBinding
    private var mBaseElevation = 0f
    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView {
        return mViews[position]
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = MainCardAdapterBinding.inflate(inflater)
        binding.contentText.text = mData[position].mTextResource
        if (mBaseElevation == 0f){
            mBaseElevation = binding.cardView.cardElevation
        }
        binding.cardView.maxCardElevation = mBaseElevation * MAX_ELEVATION_FACTOR

        mViews.add(binding.cardView)
        container.addView(binding.root)
        return binding.root
    }
    fun addCardItem(item: CardItem) {
        mData.add(item)
    }



}
