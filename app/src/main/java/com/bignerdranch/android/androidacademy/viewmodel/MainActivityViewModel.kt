package com.bignerdranch.android.androidacademy.viewmodel

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.androidacademy.data.User
import com.bignerdranch.android.androidacademy.model.repository.FirebaseRepository
import com.bignerdranch.android.androidacademy.model.repository.FirebaseRepositoryImpl



class MainActivityViewModel : ViewModel() {

    private var mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun getFireBaseRepository(firebaseUser: User, uid: String) {
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }

}