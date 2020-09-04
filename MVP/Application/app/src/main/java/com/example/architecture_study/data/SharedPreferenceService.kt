package com.example.architecture_study.data

import android.content.Context
import android.net.Uri
import android.preference.PreferenceManager

class SharedPreferenceService (context: Context) {

    private val sharedPreference by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getUserName(): String? =
        sharedPreference.getString("USER_NAME", "")

    fun setUserName(value: String) {
        sharedPreference.edit()
            .putString("USER_NAME", value)
            .apply()
    }

    fun getUserProfileImage(): String? =
        sharedPreference.getString("USER_PROFILE_IMAGE", "")

    fun setUserProfileImage(value: String) {
        sharedPreference.edit()
            .putString("USER_PROFILE_IMAGE", value)
            .apply()
    }
}
