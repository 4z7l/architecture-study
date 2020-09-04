package com.example.architecture_study.mvp.register.presenter

import com.example.architecture_study.mvp.service.FirebaseService

class RegisterPresenter(private var view : RegisterContract.View) : RegisterContract.Presenter{

    private var firebaseService = FirebaseService()

    override fun onRegistrationClicked(email: String, pwd: String) {
        view.showDialog()

        if (firebaseService.registerEvent(email,pwd)){
            view.dismissDialog()
            view.onRegistrationSuccess()
        }else{
            view.dismissDialog()
            view.onRegistrationFailure()
        }
    }

}