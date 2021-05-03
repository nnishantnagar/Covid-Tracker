package com.example.covidtracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class CaseListAdapter(): RecyclerView.Adapter<CaseViewHolder>() {
    private val items: ArrayList<globalcase> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cases,parent,false)
        val viewHolder = CaseViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder:CaseViewHolder, position: Int) {
        val currentItem = items[position]
        holder.state.text = currentItem.State
        holder.confirmed.text= currentItem.confirmed
        holder.active.text = currentItem.active
        holder.deaths.text = currentItem.deaths
        holder.recovered.text=currentItem.recovered
    }
    fun updatedNews(updatedNews: ArrayList<globalcase>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}
class CaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val state: TextView = itemView.findViewById(R.id.textView11)
    val confirmed: TextView = itemView.findViewById(R.id.textView12)
    val active: TextView = itemView.findViewById(R.id.textView21)
    val deaths: TextView = itemView.findViewById(R.id.textView32)
    val recovered: TextView = itemView.findViewById(R.id.textView2121)
}