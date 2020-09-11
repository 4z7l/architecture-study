package com.example.architecture_study.presenter

interface BasePresenter<T> {
    fun takeView(view: T)
    fun dropView()
}