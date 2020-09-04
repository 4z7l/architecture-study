package com.example.architecture_study.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.architecture_study.MainActivity
import com.example.architecture_study.R
import com.example.architecture_study.base.BaseActivity
import com.example.architecture_study.login.LoginContract
import com.example.architecture_study.login.presenter.LoginPresenter
import com.example.architecture_study.main.MainContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_social_login.*
import kotlinx.android.synthetic.main.activity_social_login.google_sign_in_button

class LoginActivity : BaseActivity<LoginContract.View, LoginContract.Presenter>(), LoginContract.View {

    override var mPresenter: LoginContract.Presenter = LoginPresenter()
    private val GOOGLE_REQUEST_CODE_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.checkSignedIn()
    }

    fun initView() {
        google_sign_in_button.setOnClickListener{
            mPresenter.googleSignIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_REQUEST_CODE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            mPresenter.handleSignInResult(task)
        }
    }

    override fun startMainActivity(currentUser: FirebaseUser?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun startGoogleLoginActivity(signInIntent: Intent) {
        startActivityForResult(signInIntent, GOOGLE_REQUEST_CODE_SIGN_IN)
    }

    override fun showProgressBar() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressbar.visibility = View.GONE
    }

}