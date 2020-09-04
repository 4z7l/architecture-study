package com.example.architecture_study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.architecture_study.base.BaseActivity
import com.example.architecture_study.data.SharedPreferenceService
import com.example.architecture_study.login.LoginContract
import com.example.architecture_study.login.presenter.LoginPresenter
import com.example.architecture_study.login.view.LoginActivity
import com.example.architecture_study.main.MainContract
import com.example.architecture_study.main.MainPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    override var mPresenter: MainContract.Presenter = MainPresenter()
    lateinit var sharedPreferenceService : SharedPreferenceService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferenceService = SharedPreferenceService(this)

        updateUserInformation()

        sign_out_button.setOnClickListener{
            mPresenter.signOut()
        }
    }

    override fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun updateUserInformation() {
        user_detail_text.text = (sharedPreferenceService.getUserName())
    }
}