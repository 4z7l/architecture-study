package com.example.architecture_study.login

import android.content.Intent
import com.example.architecture_study.base.BasePresenter
import com.example.architecture_study.base.BaseView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser

//object : Singleton 클래스 / 익명 클래스 객체
//view에는 if문 x
//presenter는 android api 아예 x << 제일 좋음!

object LoginContract {
    interface View : BaseView {
        fun startMainActivity(currentUser: FirebaseUser?)
        fun startGoogleLoginActivity(signInIntent: Intent)

    }

    interface Presenter : BasePresenter<View> {
        fun checkSignedIn()
        fun googleSignIn()
        fun handleSignInResult(task: Task<GoogleSignInAccount>)
    }
}