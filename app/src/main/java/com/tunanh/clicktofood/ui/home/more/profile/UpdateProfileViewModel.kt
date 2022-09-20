package com.tunanh.clicktofood.ui.home.more.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateProfileViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var user = MutableLiveData<User>()

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            user.value = localRepository.getUser()
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            localRepository.updateUser(user)

        }
    }
}

