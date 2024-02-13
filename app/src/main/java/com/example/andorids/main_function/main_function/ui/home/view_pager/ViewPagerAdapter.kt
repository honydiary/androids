package com.example.andorids.main_function.main_function.ui.home.view_pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andorids.R
import com.example.andorids.databinding.MainCardAdapterBinding

class ViewPagerAdapter(): RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    var goodSentences = mutableListOf<String>()
    inner class PagerViewHolder(private val binding : MainCardAdapterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(sentence:String){
            binding.contentText.text = sentence
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = MainCardAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return goodSentences.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(goodSentences[position])
    }
}