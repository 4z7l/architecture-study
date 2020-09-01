package com.example.architecture_study

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

public interface LoginContract {
    interface View {
        fun updateUIByUser(user : FirebaseUser?)
    }

    interface Presenter {
        fun setView(view : View, context : Context)
        fun checkSignedIn()

        fun googleSignIn() : Intent

        fun googleSignInTask(isTaskSuccesful : Boolean)

        fun getAuth(): FirebaseAuth
    }
}