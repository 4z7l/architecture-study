package com.example.architecture_study.base

import android.content.Context

interface BaseView {
    fun getContext(): Context

    fun showError(error: String?)

    fun showMessage(message: String?)
}