package com.bignerdranch.android.androidacademy.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bignerdranch.android.androidacademy.data.Movies
import com.bignerdranch.android.androidacademy.R
import com.bignerdranch.android.androidacademy.model.apis.ApiInterface
import com.bignerdranch.android.androidacademy.viewmodel.MoviesViewModel

import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDetailsActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var releaseData: TextView
    private lateinit var score: TextView
    private lateinit var overview: TextView
    private lateinit var banner: ImageView

    private val mViewModel: MoviesViewModel = MoviesViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
        init()
        mViewModel.getMovies()

        val field = "id" //id=search
        val search = "2022"
        val apiInterface = ApiInterface.create().getMovies("ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06", field, search, "1")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {

                Log.d("testLogs", "onResponse: ${response?.body()} ")
                title.text          = response?.body()?.name.toString()
                releaseData.text    = response?.body()?.year.toString()
                score.text          = response?.body()?.rating?.kp.toString()
                overview.text       = response?.body()?.description.toString()
                Picasso.get().load(response?.body()?.poster?.previewUrl).into(banner)
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onFailure: ${t?.message}")
            }

        })

    }

    private fun init() {
        title = findViewById(R.id.movies_details_title)
        releaseData = findViewById(R.id.movies_details_body_date_label)
        score = findViewById(R.id.movies_details_score)
        overview = findViewById(R.id.movies_details_body_overview)
        banner = findViewById(R.id.movies_details_image_banner)
    }
}
