package com.example.newsrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager= LinearLayoutManager(this)
        val items = fetchdata()
        val adapter : NewsLyoutAdepater = NewsLyoutAdepater(items,this)
        recyclerView.adapter= adapter

    }

    private fun fetchdata() : ArrayList<String>{
        val list = ArrayList<String>()
        for(i in 0 until 100){
            list.add("Iteam $i")
        }
        return list
    }

    override fun onItemClick(item: String) {
        Toast.makeText(this,"Item selectd is $item ",Toast.LENGTH_LONG).show()
    }
}