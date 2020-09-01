package com.example.architecture_study.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.architecture_study.LoginContract
import com.example.architecture_study.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LoginPresenter : LoginContract.Presenter{
    private lateinit var view : LoginContract.View
    private lateinit var context : Context
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun setView(view: LoginContract.View, context : Context) {
        this.view = view
        this.context = context

        //Firebase 로그인 관리하는 Object
        auth = FirebaseAuth.getInstance()

        // 구글 로그인 구성
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        //구글 로그인 클래스
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    // 3. Presenter는 Model(auth)로부터 유저정보를 받아 view로 전달, view Update
    override fun checkSignedIn() {
        view.updateUIByUser(auth.currentUser)
    }

    override fun googleSignIn(): Intent {
        return googleSignInClient?.signInIntent
    }

    // 9. task가 정상적이면 view 업데이트
    override fun googleSignInTask(isTaskSuccesful : Boolean) {
        if (isTaskSuccesful) {
            view.updateUIByUser(auth.currentUser)
        } else {
            view.updateUIByUser(null)
        }
    }

    override fun getAuth(): FirebaseAuth {
        return auth
    }

}