package com.tunanh.clicktofood.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) :
    BaseViewModel() {
    var intro = MutableLiveData<Boolean>()
    var user = MutableLiveData<Boolean>()


    init {

        viewModelScope.launch {
            intro.value = appPreferences.getIntro()
            user.value = appPreferences.getUser()


        }
    }


}