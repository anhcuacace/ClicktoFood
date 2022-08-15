package com.tunanh.clicktofood.data.local

import android.content.SharedPreferences
import com.tunanh.clicktofood.ui.main.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(private val preferences: SharedPreferences) {
    fun setIntro(intro: Boolean) {
        preferences.edit().putBoolean(MainActivity.INTRO, intro).apply()
    }

    fun setUser(user: Boolean) {
        preferences.edit().putBoolean(MainActivity.USER, user).apply()
    }

    fun getIntro(): Boolean {
        return preferences.getBoolean(MainActivity.INTRO, false)
    }

    fun getUser(): Boolean {
        return preferences.getBoolean(MainActivity.USER, false)
    }

    fun setEmail(email: String) {
        preferences.edit().putString(MainActivity.EMAIL, email).apply()
    }

    fun getEmail(): String {
        return preferences.getString(MainActivity.EMAIL, "").toString()
    }
}