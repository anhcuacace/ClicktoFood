package com.tunanh.clicktofood.ui.main

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.ActivityMainBinding
import com.tunanh.clicktofood.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun layoutRes(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {

    }
    companion object {
        const val INTRO = "intro"
        const val EMAIL = "email"
        const val TOKEN = "token"
    }
}