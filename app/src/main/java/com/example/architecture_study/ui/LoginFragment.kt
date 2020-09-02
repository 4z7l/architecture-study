package com.example.architecture_study.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.architecture_study.R
import com.example.architecture_study.viewmodel.LoginViewModel
import com.example.architecture_study.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private var viewModelFactory: ViewModelFactory = ViewModelFactory()

    private val viewModel : LoginViewModel
        get() =  ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View =  inflater.inflate(R.layout.fragment_login, container, false)

        attachClickListener()

        return view
    }

    private fun attachClickListener(){
        button_login.setOnClickListener {
            et_email.text.toString()
            et_password.text.toString()
        }
        textview_register.setOnClickListener {

        }
    }



}