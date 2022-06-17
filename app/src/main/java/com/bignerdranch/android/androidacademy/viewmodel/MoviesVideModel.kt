package com.bignerdranch.android.androidacademy.viewmodel


import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.androidacademy.data.Movies

import com.bignerdranch.android.androidacademy.model.repository.MoviesDBRepository
import com.bignerdranch.android.androidacademy.model.repository.MoviesDBRepositoryImpl
import com.bignerdranch.android.androidacademy.view.MoviesDetailsActivity
import com.bignerdranch.android.androidacademy.view.adapter.CustomAdapter
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response



class MoviesViewModel {

    private val _movies = MutableLiveData<List<Movies?>>()
    val movies: LiveData<List<Movies?>> = _movies

    private val mMoviesDBRepository: MoviesDBRepository = MoviesDBRepositoryImpl()
    val data = ArrayList<Movies>()


    fun getMovies() {
        val response = mMoviesDBRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "onResponse: ${response?.body()} ")

                data.add(response?.body()!!)
                _movies.postValue(data)
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onFailure: ${t?.message}")
            }
        })
    }
}