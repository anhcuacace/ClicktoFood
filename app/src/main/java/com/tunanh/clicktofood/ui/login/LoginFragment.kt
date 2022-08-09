package com.tunanh.clicktofood.ui.login

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentLoginBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_login

    override fun viewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun initView() {

    }


}