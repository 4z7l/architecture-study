package com.example.architecture_study

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

public interface LoginContract {
    //view에는 if문 x presenter는 android api 아예 x << 제일 좋음!
    // BaseView, BasePresenter 사용
    interface View {
        fun updateUIByUser(user : FirebaseUser?)
        // fun getContext()
    }

    interface Presenter {
        fun setView(view : View, context : Context)
        fun checkSignedIn()

        fun googleSignIn() : Intent

        fun googleSignInTask(isTaskSuccesful : Boolean)

        fun getAuth(): FirebaseAuth
    }
}