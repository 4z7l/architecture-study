package com.example.architecture_study.login.presenter

import android.content.Intent
import android.util.Log
import android.view.View
import com.example.architecture_study.login.LoginContract
import com.example.architecture_study.base.BasePresenterImpl
import com.example.architecture_study.data.FirebaseUserService
import com.example.architecture_study.data.SharedPreferenceService
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*

class LoginPresenter : BasePresenterImpl<LoginContract.View>(), LoginContract.Presenter {

    var firebaseUserService = FirebaseUserService()
    lateinit var sharedPreferenceService : SharedPreferenceService
    var loginIntent : Intent? = null

    override fun attachView(view: LoginContract.View) {
        super.attachView(view)
        sharedPreferenceService = SharedPreferenceService(mView?.getContext()!!)

        // ?. : safe call(Kotlin은 기본적으로 Not Null 이지만 Null을 가능하게 함)
        // !! : Null일리가 없다!!
    }

    override fun checkSignedIn() {
        if(firebaseUserService.mAuth.currentUser!=null)
            mView?.startMainActivity(firebaseUserService.mAuth.currentUser)
    }

    override fun googleSignIn() {
        mView?.showProgressBar()
        if(loginIntent == null)
            loginIntent =  firebaseUserService.getGoogleSignInIntent(mView?.getContext()!!)
        mView?.startGoogleLoginActivity(loginIntent!!)
    }

    override fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        val account = task.getResult(ApiException::class.java)!!
        firebaseAuthWithGoogle(account)
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        firebaseUserService.getAuthWithGoogle(account)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    sharedPreferenceService.setUserName(account.displayName!!)
                    sharedPreferenceService.setUserProfileImage(account.photoUrl.toString())
                    mView?.startMainActivity(firebaseUserService.mAuth.currentUser)
                } else {
                    mView?.showMessage("로그인 안됨")
                    mView?.hideProgressBar()
                }
            }
    }

}