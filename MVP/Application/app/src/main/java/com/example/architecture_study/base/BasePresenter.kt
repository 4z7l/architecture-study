package com.example.architecture_study.base

interface BasePresenter <in VIEW : BaseView> {
    fun attachView(view: VIEW)

    fun detachView()
}