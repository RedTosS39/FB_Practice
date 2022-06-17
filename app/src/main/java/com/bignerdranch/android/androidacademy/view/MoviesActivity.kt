package com.bignerdranch.android.androidacademy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidacademy.view.adapter.CustomAdapter
import com.bignerdranch.android.androidacademy.R
import com.bignerdranch.android.androidacademy.model.repository.ItemClickListenerRepository
import com.bignerdranch.android.androidacademy.viewmodel.MoviesViewModel


class MoviesActivity : AppCompatActivity(), ItemClickListenerRepository {

    private var mViewModel: MoviesViewModel = MoviesViewModel()
    private lateinit var mRecyclerview: RecyclerView
    private lateinit var mMoviesAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initViews()

        mViewModel.getMovies()
        initObservers()

    }


    private fun initObservers() {
        mViewModel.apply {
            movies.observe(this@MoviesActivity) {

                mMoviesAdapter = CustomAdapter(it, this@MoviesActivity)
                mRecyclerview.adapter = mMoviesAdapter
            }
        }
    }



    private fun initViews() {
        mRecyclerview = findViewById(R.id.recycleView)
        mRecyclerview.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(this@MoviesActivity, MoviesDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}