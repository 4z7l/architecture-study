package com.example.architecture_study.main

import com.example.architecture_study.base.BasePresenter
import com.example.architecture_study.base.BaseView

object MainContract {
    interface View : BaseView {
        fun startLoginActivity()
        fun updateUserInformation()
    }
    interface Presenter : BasePresenter<View>{
        fun signOut()
    }
}