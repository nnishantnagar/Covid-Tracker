package com.example.covidtracker

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private lateinit var madapter: CaseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        madapter = CaseListAdapter()
        recyclerView.adapter = madapter
        onRefresh()

    }
    fun onRefresh(){
        val swipeRefreshLayout=findViewById<SwipeRefreshLayout>(R.id.swipe)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        swipeRefreshLayout.setOnRefreshListener {
            fetchData()
            madapter = CaseListAdapter()
            recyclerView.adapter = madapter
            swipeRefreshLayout.isRefreshing=false
        }
    }
    fun fetchData(){
        val url ="https://api.covid19india.org/data.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
                {
                    val newsJsonArray = it.getJSONArray("statewise")
                    val caseArray = ArrayList<globalcase>()
                    for (i in 0 until newsJsonArray.length()) {
                        val newsJsonObject = newsJsonArray.getJSONObject(i)
                        val news = globalcase(
                            newsJsonObject.getString("state"),
                            newsJsonObject.getString("confirmed"),
                                newsJsonObject.getString("active"),
                                newsJsonObject.getString("recovered"),
                                newsJsonObject.getString("deaths")
                        )
                        caseArray.add(news)
                    }
                    madapter.updatedNews(caseArray)
                },
                {
                    Toast.makeText(this,"error",Toast.LENGTH_LONG).show()
                }
        )
// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun newsclicked(view: View) {
        val intentp = Intent(this, MainActivity2::class.java)
        startActivity(intentp)
    }
}