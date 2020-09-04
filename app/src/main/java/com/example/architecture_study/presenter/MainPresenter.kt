package com.example.architecture_study.presenter

import android.content.Context
import com.example.architecture_study.contract.Contract
import com.example.architecture_study.model.MainModel
import com.nhn.android.naverlogin.OAuthLogin

class MainPresenter: Contract.LoginPresenter {

    private var mainView: Contract.LoginView? = null
    private var model: MainModel? = null

    override fun getUser(mOAuthLoginModule: OAuthLogin, context: Context) {
        val user = model!!.getUserInformation(mOAuthLoginModule, context) // model에 데이터 요청

        /* 가져온 데이터 정제 */
        val sb = StringBuilder()
        sb.append("Id : ${user.id}\n")
        sb.append("name: ${user.name}\n")
        sb.append("nickname: ${user.nickname}\n")
        sb.append("email: ${user.email}\n")
        sb.append("gender: ${user.gender}\n")
        sb.append("age: ${user.age}\n")
        sb.append("birthday: ${user.birthday}\n")

        mainView?.showUser(sb.toString()) // 데이터 가져온걸로 view ui 조작
    }

    override fun takeView(view: Contract.LoginView) {
        mainView = view
        model = MainModel()
    }

    override fun dropView() {
        mainView = null
    }
}