package com.bignerdranch.android.androidacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val data = ArrayList<Movies>()
        val recyclerview = findViewById<RecyclerView>(R.id.recycleView)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val token = "ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06"
        val search = "326"
        val field = "id"

        val apiInterface = ApiInterface.create().getMovies(token, field, search)

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<Movies>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("logs", "onResponse: ${response?.body()} ")

                if (response?.body() != null) {
                    data.add(response.body()!!)

                    val adapter = CustomAdapter(data, this)
                    recyclerview.adapter = adapter

                }
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onFailure: ${t?.message}")

            }

            override fun onItemClick(position: Int) {
                Toast.makeText(this@MoviesActivity, "on Item Clicked", Toast.LENGTH_SHORT).show()
            }
        })


    }
}