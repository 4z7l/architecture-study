package com.example.architecture_study.view

import android.os.Bundle
import android.util.Log
import com.example.architecture_study.R
import com.example.architecture_study.contract.Contract
import com.example.architecture_study.presenter.MainPresenter
import com.example.architecture_study.util.Constant
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLogin.mOAuthLoginHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), Contract.LoginView {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.takeView(this) // onCreate 에서 View와 Presenter 연결

        init()
    }

    private fun init(){

        var mOAuthLoginModule = OAuthLogin.getInstance().also {
            it.init(this,
                Constant.OAUTH_CLIENT_ID,
                Constant.OAUTH_CLIENT_SECRET,
                Constant.OAUTH_CLIENT_NAME)
        };

        /* 로그인 */
        btn_custom_login.setOAuthLoginHandler(mOAuthLoginHandler)

        /* 로그아웃 */
        btn_custom_logout.setOnClickListener {
            mOAuthLoginModule.logoutAndDeleteToken(this)
            Log.e("TAG", "로그아웃 되었습니다!")
        }

        /* 정보 보여주기 */
        btn_show_information.setOnClickListener {
            presenter.getUser(mOAuthLoginModule, applicationContext) // presenter에 데이터 처리 요청
        }
    }

    override fun initPresenter() {
        presenter = MainPresenter()
    }

    override fun showUser(text: String) {
        tv_user_information.text = text
    }

    override fun showError(error: String) {
        //
    }
}