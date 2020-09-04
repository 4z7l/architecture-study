package com.example.architecture_study.main

import com.example.architecture_study.base.BasePresenterImpl
import com.example.architecture_study.data.FirebaseUserService

class MainPresenter : BasePresenterImpl<MainContract.View>(), MainContract.Presenter {

    var firebaseUserService = FirebaseUserService()

    override fun signOut() {
        firebaseUserService.mAuth.signOut()
        mView?.startLoginActivity()
    }
}