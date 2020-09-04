package com.example.architecture_study.mvp.login.presenter

interface LoginContract {

    interface View {
        fun onLoginSuccess()
        fun onLoginFailure()
        fun showDialog()
        fun dismissDialog()
    }

    interface Presenter{
        fun onLoginClicked(email:String,pwd:String)
    }
}