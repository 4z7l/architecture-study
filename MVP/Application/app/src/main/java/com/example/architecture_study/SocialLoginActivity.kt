package com.example.architecture_study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.architecture_study.presenter.LoginPresenter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_social_login.*

class SocialLoginActivity : AppCompatActivity(), LoginContract.View{
    private lateinit var presenter : LoginPresenter
    private val GOOGLE_REQUEST_CODE_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_login)

        // 1. Presenter 생성 후 View 연결
        presenter = LoginPresenter()
        presenter.setView(this,this)

        initView()
    }

    // 2. 액티비티 시작 시 사용자가 로그인되어 있는지 presenter로부터 확인
    override fun onStart() {
        super.onStart()

        presenter.checkSignedIn()
    }

    fun initView() {
        // 4. 사용자가 버튼 클릭 시 presenter를 호출해 Model(auth)의 정보를 받아 다음 액티비티 호출
        google_sign_in_button.setOnClickListener{
            var signInIntent = presenter.googleSignIn()

            // * presenter에서 view.startActivityForResult 호출이 안돼서 view에서 startActivityForResult를 호출함
            startActivityForResult(signInIntent, GOOGLE_REQUEST_CODE_SIGN_IN)
        }
    }

    // 6. Firebase 연결
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_REQUEST_CODE_SIGN_IN) {
            // * 이 부분을 presenter로 옮겨야 할 것 같은데 task를 Presenter에서 다룰 수 없어서 그냥 둠..
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // 7-1. 로그인 성공 시 GoogleSignInAccount에서 정보 가져옴
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // 7-2. 로그인 실패
                Log.w("SEULGI", "Google sign in failed", e)
            }
        }
    }

    // 8. 전달받은 ID 토큰을 통해 task 수행
    // * ID 토큰은 사용자 입력이 아닌데 presenter에서 task를 처리해도 되나?
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        presenter.getAuth().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                presenter.googleSignInTask(task.isSuccessful)
            }
    }

    override fun updateUIByUser(user : FirebaseUser?){
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}