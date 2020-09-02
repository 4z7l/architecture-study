package com.example.architecture_study.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.architecture_study.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginFragment = LoginFragment()

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)

        if (fragment == null) {
            fragment = loginFragment
            addFragmentToActivity(
                supportFragmentManager,
                fragment,
                R.id.contentFrame
            )
        }
    }

    fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId : Int){
        Preconditions.checkNotNull(fragmentManager)
        Preconditions.checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }
}