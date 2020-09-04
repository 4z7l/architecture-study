package com.example.architecture_study.mvp.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.architecture_study.R
import com.example.architecture_study.mvp.login.LoginActivity
import com.example.architecture_study.mvp.register.presenter.RegisterContract
import com.example.architecture_study.mvp.register.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private val registerPresenter : RegisterPresenter by lazy{
        RegisterPresenter(this@RegisterActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        clickEvent()
    }

    private fun clickEvent(){
        btn_register.setOnClickListener {
            val email = findViewById<EditText>(R.id.et_regist_email).text.toString()
            val pwd = findViewById<EditText>(R.id.et_regist_password).text.toString()

            registerPresenter.onRegistrationClicked(email, pwd)
        }
        btn_goto_login.setOnClickListener {
            Log.d("click","gotologin")
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRegistrationSuccess() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }


    override fun onRegistrationFailure() {
        Toast.makeText(applicationContext,"Sorry registration failed", Toast.LENGTH_SHORT).show()
    }

    override fun showDialog() {
        findViewById<ProgressBar>(R.id.pb_register_activity).visibility = View.VISIBLE
    }

    override fun dismissDialog() {
        findViewById<ProgressBar>(R.id.pb_register_activity).visibility = View.GONE
    }
}