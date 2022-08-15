package com.tunanh.clicktofood.ui.home.main

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentHomeBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {

    }


}