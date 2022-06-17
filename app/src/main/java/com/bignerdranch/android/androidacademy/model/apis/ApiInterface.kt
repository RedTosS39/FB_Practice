package com.bignerdranch.android.androidacademy.model.apis

import com.bignerdranch.android.androidacademy.data.Movies

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {
    @Headers(
            "Accept: application/json",
            "Content-type:application/json"
    )

    @GET("movie")
    fun getMovies(
            @Query("token") token: String,
            @Query("field") fieldId: String,
            @Query("search") search: String,
            @Query("limit") limit: String

    ) : Call<Movies>

    companion object {

      var BASE_URL = "https://api.kinopoisk.dev/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}