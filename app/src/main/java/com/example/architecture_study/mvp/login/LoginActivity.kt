package com.example.architecture_study.mvp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.architecture_study.mvp.login.presenter.LoginContract
import com.example.architecture_study.mvp.login.presenter.LoginPresenter
import com.example.architecture_study.mvp.MainActivity
import com.example.architecture_study.R
import com.example.architecture_study.mvp.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

open class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val loginPresenter : LoginPresenter by lazy{
        LoginPresenter(this@LoginActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        clickEvent()
    }

    private fun clickEvent(){
        btn_goto_register.setOnClickListener {
            Log.d("click","gotoRegoster")
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            val email = findViewById<EditText>(R.id.et_email).text.toString()
            val pwd = findViewById<EditText>(R.id.et_password).text.toString()

            loginPresenter.onLoginClicked(email,pwd)
        }
    }

    override fun onLoginSuccess() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginFailure() {
        Toast.makeText(applicationContext, "Sorry login id or pwd do not match", Toast.LENGTH_SHORT).show()
    }

    override fun showDialog() {
        findViewById<ProgressBar>(R.id.pb_login_activity).visibility = View.VISIBLE
    }

    override fun dismissDialog() {
        findViewById<ProgressBar>(R.id.pb_login_activity).visibility = View.GONE
    }


}