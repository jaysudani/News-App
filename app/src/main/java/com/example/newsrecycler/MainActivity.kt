package com.example.newsrecycler

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.json.JSONArray

class MainActivity : AppCompatActivity(), NewsItemClick {
    private lateinit var mAdapter : NewsLyoutAdepater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        fetchdata()
        mAdapter = NewsLyoutAdepater(this)
        recyclerView.adapter= mAdapter

    }

    private fun fetchdata(){
        val url= "https://newsapi.org/v2/top-headlines?country=us&apiKey=b4ec34d7373647bdb132268273b91cce"
        Log.d("response error 1","api check")
        val jsonObjectRequest = object:JsonObjectRequest(Request.Method.GET,url,null,
            {
                Log.d("response error 2","api check")
                val NewsJsonArray = it.getJSONArray("articles")
                Log.d("response error 3","api check")
                val NewsArray= ArrayList<News>()
                for(i in 0 until NewsJsonArray.length()){
                    val NewsjsonObject = NewsJsonArray.getJSONObject(i)
                    val news = News(
                        NewsjsonObject.getString("title"),
                        NewsjsonObject.getString("author"),
                        NewsjsonObject.getString("url"),
                        NewsjsonObject.getString("urlToImage")

                    )
                    NewsArray.add(news)
                }
                mAdapter.updatedNews(NewsArray)
            },
            {
                Log.d("response error 4","api check")
                Toast.makeText(this,"Something went Wrong",Toast.LENGTH_SHORT).show()
            })
            {
                @Throws(AuthFailureError::class)
                override fun getHeaders():Map<String,String>?{
                    val headers = HashMap<String,String>()
                    headers["User-Agent"]="Mozilla/5.0"
                    return headers
                }
            }
// Access the RequestQueue through your singleton class.
        Mysingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onItemClick(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
        //Toast.makeText(this,"Item selectd is",Toast.LENGTH_LONG).show()
    }
}