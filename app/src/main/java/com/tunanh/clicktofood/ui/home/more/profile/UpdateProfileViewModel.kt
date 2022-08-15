package com.tunanh.clicktofood.ui.home.more.profile

import androidx.lifecycle.ViewModel
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalDatabase
import com.tunanh.clicktofood.ui.base.BaseViewModel
import javax.inject.Inject

class UpdateProfileViewModel @Inject constructor(
    private val localDatabase: LocalDatabase
) : BaseViewModel()