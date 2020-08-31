package com.example.architecture_study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sign_out_button.setOnClickListener{
            startActivity(Intent(this, SocialLoginActivity::class.java))
            finish()
            FirebaseAuth.getInstance().signOut()
        }
    }
}