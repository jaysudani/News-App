package com.example.newsrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsLyoutAdepater(private val listner : NewsItemClick) : RecyclerView.Adapter<NewsViewHolder>() {
    private val items : ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listner.onItemClick(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleview.text = currentItem.title
        holder.author.text= currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.ImageUrl).into(holder.image)
    }

    override fun getItemCount(): Int = items.size

    fun updatedNews(updateditem : ArrayList<News>){
        items.clear()
        items.addAll(updateditem)
        notifyDataSetChanged()
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleview : TextView = itemView.findViewById(R.id.title)
    val author : TextView =itemView.findViewById(R.id.author)
    val image :ImageView =itemView.findViewById(R.id.image)
}

interface NewsItemClick{
    fun onItemClick(item : News)
}