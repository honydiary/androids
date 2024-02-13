package com.example.andorids.main_function.main_function.ui.diary.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.andorids.R
import com.example.andorids.databinding.DiaryItemBinding
import com.example.andorids.databinding.MainCardAdapterBinding

class AllAdapter(context: Context) : RecyclerView.Adapter<AllAdapter.MyViewHolder>() {
    val TAG = "AllAdapter"
    val context = context
    var dateList = mutableListOf<DiaryItem>()
    inner class MyViewHolder(private val binding : DiaryItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data : DiaryItem){
            binding.titleText.text = data.title
            binding.dateText.text = data.date
            binding.iconButton.setOnClickListener{
                initPop()
            }
        }

        private fun initPop(){
            var pop = PopupMenu(context, binding.iconButton)
            pop.menuInflater.inflate(R.menu.diary_list_pop_up_menu, pop.menu)
            pop.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.mark -> {
                        Log.d(TAG, "mark")
                        return@setOnMenuItemClickListener true

                    }
                    R.id.refact -> {
                        Log.d(TAG, "refact")
                        return@setOnMenuItemClickListener true
                    }
                    R.id.delete -> {
                        Log.d(TAG, "delete")
                        return@setOnMenuItemClickListener true
                    }
                    else -> false
                }
            }
            pop.show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DiaryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dateList[position])
    }
    fun first(){

    }

    // 사용 예시
    fun main() {
        // `first` 함수를 호출하고, 콜백에서 결과를 출력합니다.
//        first { result ->
//            println("콜백에서 받은 결과: $result")
//        }
    }
}