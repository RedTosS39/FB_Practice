package com.bignerdranch.android.androidacademy.model.repository

import com.bignerdranch.android.androidacademy.data.Movies
import retrofit2.Call

interface MoviesDBRepository {
    /**
     * return list of popular [Movies]
     */

    fun getMovies() : Call<Movies>
}