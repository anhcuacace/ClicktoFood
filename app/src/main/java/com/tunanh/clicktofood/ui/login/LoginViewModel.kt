package com.tunanh.clicktofood.ui.login

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalDatabase
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val localDatabase: LocalDatabase
) : BaseViewModel() {


    init {

    }

    fun saveUser(email: String, name: String, user: Boolean,img: String,phone:String) {

        appPreferences.setUser(user)
        appPreferences.setEmail(email)
        viewModelScope.launch {
            val mUser = User(
                email = email,
                name = name,
                image = img, phone = phone
            )
            localDatabase.userDao().addUser(mUser)
        }


    }
}