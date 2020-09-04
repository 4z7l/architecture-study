package com.example.architecture_study.model

import android.content.Context
import com.example.architecture_study.retrofit.NaverAPI
import com.example.architecture_study.retrofit.data.User
import com.example.architecture_study.util.Constant
import com.nhn.android.naverlogin.OAuthLogin
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainModel {

    fun getUserInformation(mOAuthLoginModule: OAuthLogin, context: Context): User {
        var accessToken: String = mOAuthLoginModule.getAccessToken(context)

        var header = "Bearer $accessToken"
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.loginURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(NaverAPI::class.java)
        val callUserInformation = api.getInformation(header)

        var user = User()

        runBlocking {
            GlobalScope.launch {
                val response = callUserInformation.execute()
                user = response.body()!!.response
            }.join() // api에서 정보를 가져올때 까지 기다린다
        }

        return user
    }
}