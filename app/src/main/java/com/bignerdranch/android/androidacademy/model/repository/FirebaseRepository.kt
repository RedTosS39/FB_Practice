package com.bignerdranch.android.androidacademy.model.repository

import com.bignerdranch.android.androidacademy.data.User


interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid: String)
}