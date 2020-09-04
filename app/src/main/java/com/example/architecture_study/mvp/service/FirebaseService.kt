package com.example.architecture_study.mvp.service

import com.google.firebase.auth.FirebaseAuth

class FirebaseService {
    private var mAuth = FirebaseAuth.getInstance()
    private var loginSuccess = false
    private var registerSuccess = false

    fun loginEvent(email : String, pwd : String) : Boolean{
         mAuth.signInWithEmailAndPassword(email,pwd)
             .addOnCompleteListener {task ->
                 loginSuccess = task.isSuccessful
             }
        return loginSuccess
    }

    fun registerEvent(email: String, pwd: String) : Boolean{
        mAuth.createUserWithEmailAndPassword(email,pwd)
            .addOnCompleteListener {task ->
                registerSuccess = task.isSuccessful
            }
        return registerSuccess
    }

    fun logout() {
        mAuth.signOut()
    }

}