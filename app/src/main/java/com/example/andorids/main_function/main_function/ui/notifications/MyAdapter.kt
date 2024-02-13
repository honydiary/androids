package com.example.andorids.main_function.main_function.ui.notifications

// MyAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.andorids.R

class MyAdapter(private val dataSet: List<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val goodWordtextView: TextView = view.findViewById(R.id.id_profile_item_goodWrodText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.goodWordtextView.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
