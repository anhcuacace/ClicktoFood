package com.tunanh.clicktofood.ui.splash

import androidx.lifecycle.MutableLiveData
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.ui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val appPreferences: AppPreferences) :
    BaseViewModel() {
    var intro = MutableLiveData<Boolean>()
    var user = MutableLiveData<Boolean>()

    init {
        intro.value = appPreferences.getIntro()
        user.value = appPreferences.getUser()
    }
}