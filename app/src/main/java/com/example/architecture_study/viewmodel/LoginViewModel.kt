package com.example.architecture_study.viewmodel

import androidx.lifecycle.ViewModel
import com.example.architecture_study.ui.LoginViewState

class LoginViewModel : ViewModel(){

    private var viewState = LoginViewState()

    fun handleSubmitButtonClicked(email:String, password:String){
        loginWithEmailAndPassword(email, password)
    }

    private fun loginWithEmailAndPassword(email: String, password: String){
        if (!validate(email,password)){
            //유효성 check
        }
    }

    private fun validate(email: String, password: String): Boolean {

        if (email.isEmpty() || password.isEmpty()) {
            this.viewState.errorMessage = "Please fill out all the fields"
            return false
        }

        if (password.length < 6) {
            this.viewState.errorMessage = "Password must be at least 6 characters long"
            return false
        }

        viewState.errorMessage = ""
        return true
    }

}