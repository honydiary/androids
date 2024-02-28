package com.example.andorids.main_function.main_function.ui.diary.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.andorids.R
import com.example.andorids.databinding.DiaryItemBinding

class AllAdapter(context: Context, listener:test) : RecyclerView.Adapter<AllAdapter.MyViewHolder>() {
    val TAG = "AllAdapter"
    val context = context
    var dateList = mutableListOf<DiaryItem>()
    val listner = listener
    inner class MyViewHolder(private val binding : DiaryItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data : DiaryItem, position: Int){
            binding.titleText.text = data.title
            binding.dateText.text = data.date
            binding.iconButton.setOnClickListener{
                initPop(position)
            }
            binding.itemView.setOnClickListener {
                listner.call(position, "view")
            }
        }

        private fun initPop(position: Int){
            val pop = PopupMenu(context, binding.iconButton)
            pop.menuInflater.inflate(R.menu.diary_list_pop_up_menu, pop.menu)
            pop.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.mark -> {
                        listner.call(position, "mark")
                        Log.d(TAG, "mark")
                        return@setOnMenuItemClickListener true
                    }
                    R.id.refact -> {
                        listner.call(position, "refact")
                        Log.d(TAG, "refact")
                        return@setOnMenuItemClickListener true
                    }
                    R.id.delete -> {
                        listner.call(position, "delete")
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
        holder.bind(dateList[position], position)
    }


}
interface test{
    fun call(position: Int, state:String)
}