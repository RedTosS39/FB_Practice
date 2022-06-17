package com.bignerdranch.android.androidacademy.model.repository


import com.bignerdranch.android.androidacademy.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl : FirebaseRepository {

    private  var database: DatabaseReference = Firebase.database.reference
    override fun updateUserData(firebaseUser: User, uid: String) {
        database.child("currentUser").setValue(firebaseUser)
    }
}