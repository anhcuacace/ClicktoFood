package com.tunanh.clicktofood.ui.home.more.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentUpdateProfileBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class UpdateProfileFragment : BaseFragment<FragmentUpdateProfileBinding,UpdateProfileViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_update_profile

    override fun viewModelClass(): Class<UpdateProfileViewModel> =UpdateProfileViewModel::class.java

    override fun initView() {
        TODO("Not yet implemented")
    }
}