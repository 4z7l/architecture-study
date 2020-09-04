package com.example.architecture_study.contract

import android.content.Context
import com.example.architecture_study.retrofit.data.User
import com.example.architecture_study.presenter.BasePresenter
import com.example.architecture_study.view.BaseView
import com.nhn.android.naverlogin.OAuthLogin

interface Contract {

    interface LoginView: BaseView {
        fun showUser(text: String)
    }

    interface LoginPresenter: BasePresenter<LoginView> {
        fun getUser(mOAuthLoginModule: OAuthLogin, context: Context)
    }
}