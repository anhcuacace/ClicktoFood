package com.tunanh.clicktofood.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    appPreferences: AppPreferences,
    private val localRepository: LocalRepository
) :
    BaseViewModel() {
    var intro = MutableLiveData<Boolean>()
    var user = MutableLiveData<Boolean>()


    init {
        intro.value = appPreferences.getIntro()
        val email = appPreferences.getEmail()
        viewModelScope.launch {
            user.value = localRepository.isEmailIsExist(email)
        }
    }


}