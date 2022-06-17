package com.bignerdranch.android.androidacademy.model.repository

import com.bignerdranch.android.androidacademy.data.Movies
import com.bignerdranch.android.androidacademy.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {

    private val token = "ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06"
    private val field = "id" //id=search
    private val search = "2022"
    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {

        return apiInterface.getMovies(token, field, search, "10")
    }


}