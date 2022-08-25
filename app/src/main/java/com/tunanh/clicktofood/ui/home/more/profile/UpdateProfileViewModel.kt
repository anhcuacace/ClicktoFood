package com.tunanh.clicktofood.ui.home.more.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalDatabase
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class  UpdateProfileViewModel @Inject constructor(
    private val localDatabase: LocalDatabase,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var user = MutableLiveData<User>()

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            user.value = localDatabase.userDao().getUser()[0]
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            localRepository.updateUser(user)
        }

    }
}

