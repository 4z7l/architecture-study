package com.example.architecture_study.mvp.register.presenter

interface RegisterContract {

    interface View{
        fun onRegistrationSuccess()
        fun onRegistrationFailure()
        fun showDialog()
        fun dismissDialog()
    }

    interface Presenter{
        fun onRegistrationClicked(email:String,pwd:String)
    }
}