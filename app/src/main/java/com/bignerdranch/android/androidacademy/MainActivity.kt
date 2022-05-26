package com.bignerdranch.android.androidacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database

import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        init()

        registrationButton.setOnClickListener {

            val intent = Intent(this@MainActivity, RegActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        registrationButton = findViewById(R.id.registration)
        editTextEmail = findViewById(R.id.main_activity_edit_text_email)
        editTextPassword = findViewById(R.id.main_activity_edit_text_password)

    }
}
*/
        database = Firebase.database.reference
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

        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val authUSer = FirebaseAuth.getInstance().currentUser

            authUSer?.let {
                val currentUser = User(it.email, it.uid)
                database.child("currentUser").setValue(currentUser)
            }
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
}