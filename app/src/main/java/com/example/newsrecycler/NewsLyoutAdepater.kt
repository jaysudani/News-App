package com.example.newsrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsLyoutAdepater(val items : ArrayList<String>,private val listner : NewsItemClick) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener(){
            listner.onItemClick(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleview.text = currentItem
    }

    override fun getItemCount(): Int = items.size

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleview : TextView = itemView.findViewById(R.id.title)
}

interface NewsItemClick{
    fun onItemClick(item : String)
}