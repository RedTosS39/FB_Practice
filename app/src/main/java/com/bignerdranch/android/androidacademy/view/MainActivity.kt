package com.bignerdranch.android.androidacademy.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bignerdranch.android.androidacademy.R

import com.bignerdranch.android.androidacademy.viewmodel.MainActivityViewModel

import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.bignerdranch.android.androidacademy.data.User
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private val mMainActivityViewModel: MainActivityViewModel = MainActivityViewModel()

    private val signInLauncher = registerForActivityResult(
            FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onRegistrationScreen()

    }

    private fun onRegistrationScreen() {
        val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build())

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        Log.d("logTest", "onSignInResult: ${response?.email}")

        when (result.resultCode) {
            RESULT_OK -> {
                // Successfully signed in
                val authUser = FirebaseAuth.getInstance().currentUser
                Log.d("logTest", "onSignInResult: ${authUser?.email}")
                authUser?.let {

                    val firebaseUser = User(it.email, it.uid)
                    mMainActivityViewModel.getFireBaseRepository(firebaseUser, it.uid)

                    val intent = Intent(this@MainActivity, MoviesActivity::class.java)
                    startActivity(intent)
                }
            }
            RESULT_CANCELED -> {
                Toast.makeText(this@MainActivity, "Something wrong", Toast.LENGTH_SHORT).show()
            }
            else -> {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}