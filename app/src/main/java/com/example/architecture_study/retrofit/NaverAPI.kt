package com.example.architecture_study.retrofit

import com.example.architecture_study.retrofit.data.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface NaverAPI {
    @GET("v1/nid/me")
    fun getInformation(
        @Header("Authorization") header: String
    ): Call<LoginResponse>
}