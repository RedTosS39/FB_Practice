package com.bignerdranch.android.androidacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recycleView)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Movies>()

        // This loop will create 20 Views containing
        // the image with the count of view


        // This will pass the ArrayList to our Adapter


        // Setting the Adapter with the recyclerview


        val apiInterface = ApiInterface.create().getMovies(
                "ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06",
                "2000",
                "id")



        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {

                Log.d("testLogs", "fun onResponse: ${response?.body().toString()}")

                if (response?.body() != null) {

                    Log.d("testLogs", "Call: ${call.toString()} Success")


                        val adapter = CustomAdapter(listOf(response.body()) as List<Movies>)
                        recyclerview.adapter = adapter

                }
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onFailure: ${t?.message}")

            }
        })


    }
}