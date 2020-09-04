package com.example.architecture_study.mvp.login.presenter

import com.example.architecture_study.mvp.service.FirebaseService


class LoginPresenter (private val view : LoginContract.View) : LoginContract.Presenter {

    private var firebaseService = FirebaseService()

    override fun onLoginClicked(email: String, pwd: String) {
        view.showDialog()

        if (firebaseService.loginEvent(email,pwd)){
            view.onLoginSuccess()
            view.dismissDialog()
        }else{
            view.onLoginFailure()
            view.dismissDialog()
        }
    }


}