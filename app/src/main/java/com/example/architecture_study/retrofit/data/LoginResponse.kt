package com.example.architecture_study.retrofit.data
/*
 로그인에 성공하면 유저 정보를 받아옴
 */
data class LoginResponse(
    var response: User,
    var resultcode: String,
    var message: String
)