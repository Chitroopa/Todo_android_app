package com.example.todo_kotlin

import android.content.Intent

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val cardData: List<CardViewModel>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (cardData[position].priority.lowercase()) {
            "high" -> holder.itemView.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.itemView.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.itemView.setBackgroundColor(Color.parseColor("#00917C"))
        }
        val cardViewModel =  cardData[position]
        holder.taskContent.text = cardViewModel.taskContent
        holder.priority.text = cardViewModel.priority
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return cardData.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val taskContent: TextView = itemView.findViewById(R.id.taskContent)
        val priority: TextView = itemView.findViewById(R.id.priority)
    }
}